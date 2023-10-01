package co.simplon.game.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.game.configurations.AuthHelper;
import co.simplon.game.configurations.CredentialAlreadyUseException;
import co.simplon.game.dtos.user.CreateUserAccount;
import co.simplon.game.dtos.user.Credentials;
import co.simplon.game.dtos.user.TokenInfo;
import co.simplon.game.entities.user.Role;
import co.simplon.game.entities.user.UserAccount;
import co.simplon.game.repositories.RoleRepository;
import co.simplon.game.repositories.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final AuthHelper authHelper;
    private final UserRepository users;
    private final RoleRepository roles;

    public UserServiceImpl(AuthHelper authHelper,
	    UserRepository users, RoleRepository roles) {
	this.authHelper = authHelper;
	this.users = users;
	this.roles = roles;
    }

    @Override
    @Transactional
    public void signUp(CreateUserAccount inputs) {
	String email = inputs.getEmail();
	String nickname = inputs.getNickname();
	UserAccount existingAccount = users
		.findOneByEmailOrNickname(email, nickname);
	if (existingAccount != null) {
	    throw new CredentialAlreadyUseException(
		    "This email or this nickname is already used");
	}

	UserAccount user = new UserAccount();
	user.setEmail(email);
	user.setNickname(nickname);
	String password = inputs.getPassword();
	String hash = authHelper.encode(password);
	user.setPassword(hash);
	Role role = roles.findOneProjectedById(1L);
	user.setRole(role);
	users.save(user);
    }

    @Override
    public TokenInfo signIn(Credentials inputs) {
	// checking
	UserAccount candidate = users
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
	// token
	TokenInfo tokenInfo = new TokenInfo();
	List<String> roles = new ArrayList<String>();
	String role = candidate.getRole().getName();
	roles.add(role);
	String token = authHelper.createJWT(roles,
		candidate.getNickname());
	tokenInfo.setToken(token);
	tokenInfo.setRole(role);
	String nickname = candidate.getNickname();
	tokenInfo.setSubject(nickname);
	return tokenInfo;
    }

    @Override
    public void logOut(String nickname) {
    }

    @Override
    public List<String> getConnectedUsers() {
	return null;
    }

}
