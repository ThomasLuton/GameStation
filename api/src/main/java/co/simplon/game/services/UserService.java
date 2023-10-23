package co.simplon.game.services;

import java.util.List;

import co.simplon.game.dtos.user.CreateUserAccount;
import co.simplon.game.dtos.user.Credentials;
import co.simplon.game.dtos.user.TokenInfo;
import co.simplon.game.dtos.user.UserView;

public interface UserService {

    public void signUp(CreateUserAccount inputs);

    public TokenInfo signIn(Credentials inputs);

    public void logOut(String nickname);

    public List<UserView> getConnectedUsers();
}
