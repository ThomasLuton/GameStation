package co.simplon.game.players.services;

import java.util.List;

import co.simplon.game.players.dtos.PlayerSimpleView;
import co.simplon.game.players.dtos.PlayerUpdateNotification;
import co.simplon.game.players.dtos.SignIn;
import co.simplon.game.players.dtos.SignUp;
import co.simplon.game.players.dtos.TokenInfo;

public interface PlayerService {

    public void createPlayer(SignUp inputs);

    public void updatePlayerNotification(
	    PlayerUpdateNotification inputs,
	    Integer suffix);

    public TokenInfo connectPlayer(SignIn credentials);

    void logIn(String email);

    void logOut(String email);

    List<PlayerSimpleView> getConnectedUsers();

    List<PlayerSimpleView> getAllUsers();

}
