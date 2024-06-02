package co.simplon.game.players.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.game.errors.CodeError;
import co.simplon.game.errors.GameStationError;
import co.simplon.game.notifications.dtos.CreateNotification;
import co.simplon.game.notifications.services.NotificationService;
import co.simplon.game.players.dtos.GamerTagDto;
import co.simplon.game.players.dtos.PlayerOptionsView;
import co.simplon.game.players.dtos.PlayerSimpleView;
import co.simplon.game.players.dtos.PlayerUpdateAvatar;
import co.simplon.game.players.dtos.PlayerUpdateNotification;
import co.simplon.game.players.dtos.PlayerUpdatePlayerName;
import co.simplon.game.players.dtos.SignIn;
import co.simplon.game.players.dtos.SignUp;
import co.simplon.game.players.dtos.TokenInfo;
import co.simplon.game.players.entities.GamerTag;
import co.simplon.game.players.entities.Player;
import co.simplon.game.players.entities.Role;
import co.simplon.game.players.repositories.PlayerRepository;
import co.simplon.game.players.repositories.RoleRepository;
import co.simplon.game.utils.AuthHelper;
import jakarta.validation.Valid;
import reactor.core.publisher.Sinks;

@Service
@Transactional(readOnly = true)
public class PlayerServiceImpl implements PlayerService {

    private static Integer maxLenghtTag = 9999;

    @Value("${gameStation.uploads.location}")
    private String uploadDir;

    private final AuthHelper authHelper;
    private final PlayerRepository players;
    private final RoleRepository roles;
    private final NotificationService notificationService;
    private final Sinks.Many<List<PlayerSimpleView>> sink;

    @Autowired
    private SimpMessagingTemplate messageTemplate;

    public PlayerServiceImpl(AuthHelper authHelper,
	    PlayerRepository players, RoleRepository roles,
	    NotificationService notificationsService,
	    Sinks.Many<List<PlayerSimpleView>> sink) {
	this.authHelper = authHelper;
	this.players = players;
	this.roles = roles;
	this.notificationService = notificationsService;
	this.sink = sink;
    }

    @Override
    @Transactional
    public void createPlayer(SignUp inputs) {
	String email = inputs.email();
	Player existingPlayer = players
		.findOneByEmail(email);
	if (existingPlayer != null) {
	    throw new GameStationError(
		    CodeError.PlayerAlreadyExist,
		    "Email already used",
		    HttpStatus.BAD_REQUEST);
	}
	Player player = new Player();
	player.setEmail(email);
	GamerTag gamerTag = createTag(inputs.playerName());
	player.setGamerTag(gamerTag);
	player.setEmailNotificationEnable(false);
	player.setDayBeforeNotification(7);
	player.setConnection(false);
	Role role = roles.findOneProjectedByName("player");
	player.setRole(role);

	String password = inputs.password();
	String hash = authHelper.encode(password);
	player.setPassword(hash);

	players.save(player);
	CreateNotification welcomeMessage = new CreateNotification(
		"Welcome",
		"Welcome on Game station, let's have fun with some games",
		null, player);
	notificationService.create(welcomeMessage);
    }

    private GamerTag createTag(String name) {
	GamerTag tag = new GamerTag();
	tag.setPlayerName(name);
	while (true) {
	    int counter = 0;
	    Integer suffix = new Random().nextInt(1000,
		    maxLenghtTag);
	    Player existingSuffix = players
		    .findOneByGamerTagSuffix(suffix);
	    if (existingSuffix == null) {
		tag.setSuffix(suffix);
		break;
	    } else {
		counter++;
		if (counter > 5) {
		    counter = 0;
		    maxLenghtTag = maxLenghtTag * 10;
		}
	    }
	}
	return tag;
    }

    @Override
    @Transactional
    public TokenInfo connectPlayer(SignIn credentials) {
	Player candidate = players
		.findOneByEmail(credentials.email());
	if (candidate == null) {
	    throw new GameStationError(
		    CodeError.WrongCredentials,
		    "Wrong credentials",
		    HttpStatus.UNAUTHORIZED);
	}
	boolean match = authHelper.matches(
		credentials.password(),
		candidate.getPassword());
	if (!match) {
	    throw new GameStationError(
		    CodeError.WrongCredentials,
		    "Wrong credentials",
		    HttpStatus.UNAUTHORIZED);
	}
	List<String> roles = new ArrayList<String>();
	String role = candidate.getRole().getName();
	roles.add(role);
	String token = authHelper.createJWT(roles,
		candidate.getEmail(),
		candidate.getGamerTag().getSuffix()
			.longValue());
	GamerTagDto gamerTag = new GamerTagDto(
		candidate.getGamerTag().getPlayerName(),
		candidate.getGamerTag().getSuffix());
	candidate.setConnection(true);
	players.save(candidate);
	sink.tryEmitNext(getConnectedUsers());
	List<PlayerSimpleView> message = getConnectedUsers();
	messageTemplate.convertAndSend("/topic/users",
		message);
	return new TokenInfo(token, role, gamerTag);
    }

    @Override
    @Transactional
    public void logOut(Integer suffix) {
	Player player = players
		.findOneByGamerTagSuffix(suffix);
	player.setConnection(false);
	players.save(player);
	List<PlayerSimpleView> message = getConnectedUsers();
	messageTemplate.convertAndSend("/topic/users",
		message);
    }

    @Override
    public List<PlayerSimpleView> getConnectedUsers() {
	return players.findAllProjectedByConnection(true);
    }

    @Override
    public List<PlayerSimpleView> getAllUsers() {
	return players.findAllProjectedByOrderById();
    }

    @Override
    @Transactional
    public void updatePlayerNotification(
	    PlayerUpdateNotification inputs,
	    Integer suffix) {
	Player player = players
		.findOneByGamerTagSuffix(suffix);
	player.setDayBeforeNotification(
		inputs.daysBefore());
	player.setEmailNotificationEnable(
		inputs.activate());
	players.save(player);
    }

    @Override
    @Transactional
    public void updatePlayerName(
	    @Valid PlayerUpdatePlayerName inputs,
	    Integer suffix) {
	Player player = players
		.findOneByGamerTagSuffix(suffix);
	player.getGamerTag()
		.setPlayerName(inputs.playerName());
	players.save(player);
    }

    @Override
    @Transactional
    public void updateAvatar(PlayerUpdateAvatar input,
	    Integer suffix) {
	Player entity = players
		.findOneByGamerTagSuffix(suffix);

	if ((input.avatar() != null)) {
	    if (entity.getAvatar() != null) {
		Path oldAvatar = Paths.get(uploadDir,
			entity.getAvatar());
		oldAvatar.toFile().delete();
	    }
	    MultipartFile file = input.avatar();
	    String baseName = UUID.randomUUID().toString();
	    String fileName = baseName
		    + file.getOriginalFilename();
	    entity.setAvatar(fileName);
	    store(file, fileName);
	}
	players.save(entity);

    }

    private void store(MultipartFile file,
	    String fileName) {
	Path uploadedPath = Paths.get(uploadDir);
	Path target = uploadedPath.resolve(fileName);
	try (InputStream in = file.getInputStream()) {
	    Files.copy(in, target,
		    StandardCopyOption.REPLACE_EXISTING);
	} catch (IOException ex) {
	    throw new RuntimeException(ex);
	}
    }

    @Override
    public PlayerOptionsView getOneForOptions(
	    Integer suffix) {
	return players
		.findOneProjectedByGamerTagSuffix(suffix);
    }
}
