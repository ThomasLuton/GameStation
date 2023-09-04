package co.simplon.game.services;

import co.simplon.game.dtos.authentication.CreateUserAccount;
import co.simplon.game.dtos.authentication.Credentials;
import co.simplon.game.dtos.authentication.TokenInfo;

public interface UserService {

    public void signUp(CreateUserAccount inputs);

    public TokenInfo signIn(Credentials inputs);
}
