package co.simplon.game.players.services;

import java.util.List;

import co.simplon.game.players.dtos.PlayerOptionsView;
import co.simplon.game.players.dtos.PlayerSimpleView;
import co.simplon.game.players.dtos.PlayerUpdateAvatar;
import co.simplon.game.players.dtos.PlayerUpdateNotification;
import co.simplon.game.players.dtos.PlayerUpdatePlayerName;
import co.simplon.game.players.dtos.SignIn;
import co.simplon.game.players.dtos.SignUp;
import co.simplon.game.players.dtos.TokenInfo;
import jakarta.validation.Valid;

public interface PlayerService {

    public void createPlayer(SignUp inputs);

    public void updatePlayerNotification(
	    PlayerUpdateNotification inputs,
	    Integer suffix);

    void updateAvatar(PlayerUpdateAvatar input,
	    Integer suffix);

    public TokenInfo connectPlayer(SignIn credentials);

    void logIn(String email);

    void logOut(Integer suffix);

    List<PlayerSimpleView> getConnectedUsers();

    List<PlayerSimpleView> getAllUsers();

    PlayerOptionsView getOneForOptions(Integer suffix);

    public void updatePlayerName(
	    @Valid PlayerUpdatePlayerName inputs,
	    Integer suffix);

}
