package co.simplon.game.players;

import java.util.List;

import co.simplon.game.players.dtos.PlayerSimpleView;
import co.simplon.game.players.dtos.SignIn;
import co.simplon.game.players.dtos.SignUp;
import co.simplon.game.players.dtos.TokenInfo;

public interface PlayerService {

    public void createPlayer(SignUp inputs);

    public TokenInfo connectPlayer(SignIn credentials);

    void logIn(String email);

    void logOut(String email);

    List<PlayerSimpleView> getConnectedUsers();

    List<PlayerSimpleView> getAllUsers();

}
