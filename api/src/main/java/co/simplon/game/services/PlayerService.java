package co.simplon.game.services;

import java.util.List;

import co.simplon.game.dtos.user.CreatePlayer;
import co.simplon.game.dtos.user.Credentials;
import co.simplon.game.dtos.user.PlayerDetailView;
import co.simplon.game.dtos.user.TokenInfo;
import co.simplon.game.dtos.user.PlayerView;

public interface PlayerService {

    public void signUp(CreatePlayer inputs);

    public TokenInfo signIn(Credentials inputs);

    public void logOut(String nickname);

    public List<PlayerView> getConnectedUsers();

    public List<PlayerDetailView> getAllUsers();
}
