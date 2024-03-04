package co.simplon.game.players.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.game.errors.CodeError;
import co.simplon.game.errors.GameStationError;
import co.simplon.game.players.PlayerService;
import co.simplon.game.players.dtos.PlayerSimpleView;
import co.simplon.game.players.dtos.SignIn;
import co.simplon.game.players.dtos.SignUp;
import co.simplon.game.players.dtos.TokenInfo;
import co.simplon.game.players.entities.GamerTag;
import co.simplon.game.players.entities.Player;
import co.simplon.game.players.entities.Role;
import co.simplon.game.players.repositories.PlayerRepository;
import co.simplon.game.players.repositories.RoleRepository;
import co.simplon.game.utils.AuthHelper;

@Service
@Transactional(readOnly = true)
public class PlayerServiceImpl implements PlayerService {

    private static Integer maxLenghtTag = 9999;

    private final AuthHelper authHelper;
    private final PlayerRepository players;
    private final RoleRepository roles;

    public PlayerServiceImpl(AuthHelper authHelper,
	    PlayerRepository players,
	    RoleRepository roles) {
	this.authHelper = authHelper;
	this.players = players;
	this.roles = roles;
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
	player.setConnection(false);
	Role role = roles.findOneProjectedByName("player");
	player.setRole(role);

	String password = inputs.password();
	String hash = authHelper.encode(password);
	player.setPassword(hash);

	players.save(player);
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
	return new TokenInfo(token, role,
		candidate.getGamerTag().toString());
    }

    @Override
    @Transactional
    public void logIn(String email) {
	Player player = players.findOneByEmail(email);
	boolean isConnected = player.isConnection();
	if (!isConnected) {
	    player.setConnection(true);
	    players.save(player);
	} else {
	    throw new GameStationError(
		    CodeError.PlayerAlreadyConnected,
		    "Player already connected",
		    HttpStatus.CONFLICT);
	}
    }

    @Override
    @Transactional
    public void logOut(String email) {
	Player player = players.findOneByEmail(email);
	player.setConnection(false);
	players.save(player);
    }

    @Override
    public List<PlayerSimpleView> getConnectedUsers() {
	return players.findAllProjectedByConnection(true);
    }

    @Override
    public List<PlayerSimpleView> getAllUsers() {
	return players.findAllProjectedByOrderById();
    }

}
