package co.simplon.game.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.game.configurations.CredentialAlreadyUseException;
import co.simplon.game.dtos.favorite.FavoriteView;
import co.simplon.game.dtos.user.CreatePlayer;
import co.simplon.game.dtos.user.Credentials;
import co.simplon.game.dtos.user.PlayerDetailView;
import co.simplon.game.dtos.user.PlayerView;
import co.simplon.game.dtos.user.TokenInfo;
import co.simplon.game.entities.user.ConnectedPlayers;
import co.simplon.game.entities.user.Player;
import co.simplon.game.entities.user.Role;
import co.simplon.game.repositories.GameRepository;
import co.simplon.game.repositories.PlayerRepository;
import co.simplon.game.repositories.RoleRepository;
import co.simplon.game.utils.AuthHelper;

@Service
@Transactional(readOnly = true)
public class PlayerServiceImpl implements PlayerService {

    private final AuthHelper authHelper;
    private final PlayerRepository players;
    private final RoleRepository roles;
    private final ConnectedPlayers connectedPlayers;
    private final GameRepository games;
    private final FavoriteService favoriteService;

    public PlayerServiceImpl(AuthHelper authHelper,
	    PlayerRepository users, RoleRepository roles,
	    ConnectedPlayers connectedPlayers,
	    GameRepository games,
	    FavoriteService favoriteService) {
	this.authHelper = authHelper;
	this.players = users;
	this.roles = roles;
	this.connectedPlayers = connectedPlayers;
	this.games = games;
	this.favoriteService = favoriteService;
    }

    @Override
    @Transactional
    public void signUp(CreatePlayer inputs) {
	String email = inputs.getEmail();
	String nickname = inputs.getNickname();
	Player existingAccount = players
		.findOneByEmailOrNickname(email, nickname);
	if (existingAccount != null) {
	    throw new CredentialAlreadyUseException(
		    "This email or this nickname is already used");
	}

	Player user = new Player();
	user.setEmail(email);
	user.setNickname(nickname);
	String password = inputs.getPassword();
	String hash = authHelper.encode(password);
	user.setPassword(hash);
	Role role = roles.findOneProjectedById(1L);
	user.setRole(role);
	players.save(user);
    }

    @Override
    public TokenInfo signIn(Credentials inputs) {
	// checking
	Player candidate = players
		.findOneByEmail(inputs.getEmail());
	if (candidate == null) {
	    throw new BadCredentialsException(
		    "Wrong credentials");
	}
	boolean match = authHelper.matches(
		inputs.getPassword(),
		candidate.getPassword());
	if (!match) {
	    throw new BadCredentialsException(
		    "Wrong credentials");
	}
	this.connectedPlayers.getUsers().add(candidate);
	// token
	TokenInfo tokenInfo = new TokenInfo();
	List<String> roles = new ArrayList<String>();
	String role = candidate.getRole().getName();
	roles.add(role);
	String token = authHelper.createJWT(roles,
		candidate.getNickname());
	tokenInfo.setToken(token);
	tokenInfo.setRole(role);
	tokenInfo.setId(candidate.getId());
	String nickname = candidate.getNickname();
	tokenInfo.setSubject(nickname);
	tokenInfo.setFavorites(
		favoriteService.getAllFavoriteForOnePlayer(
			candidate.getId()));
	return tokenInfo;
    }

    @Override
    public void logOut(String nickname) {
	Player user = players.findOneByNickname(nickname);
	this.connectedPlayers.getUsers().remove(user);
    }

    @Override
    public List<PlayerView> getConnectedUsers() {
	List<PlayerView> connectedUsers = new ArrayList<PlayerView>();
	for (Player user : this.connectedPlayers
		.getUsers()) {
	    PlayerView playerView = new PlayerView();
	    playerView.setNickName(user.getNickname());
	    connectedUsers.add(playerView);
	}
	return connectedUsers;
    }

    @Override
    public List<PlayerDetailView> getAllUsers() {
	List<Player> allPlayers = players
		.findAllProjectedByOrderById();
	List<PlayerDetailView> views = new ArrayList<PlayerDetailView>();
	for (Player player : allPlayers) {
	    PlayerDetailView view = new PlayerDetailView();
	    view.setNickName(player.getNickname());
	    List<FavoriteView> favorites = favoriteService
		    .getAllFavoriteForOnePlayer(
			    player.getId());
	    view.setFavorites(favorites);
	    views.add(view);
	}
	return views;
    }

}
