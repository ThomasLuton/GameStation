package co.simplon.game.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.game.configurations.AuthHelper;
import co.simplon.game.dtos.authentication.CreateUserAccount;
import co.simplon.game.dtos.authentication.Credentials;
import co.simplon.game.dtos.authentication.TokenInfo;
import co.simplon.game.entities.authentication.Role;
import co.simplon.game.entities.authentication.UserAccount;
import co.simplon.game.repositories.RoleRepository;
import co.simplon.game.repositories.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private AuthHelper authHelper;
    private UserRepository users;
    private RoleRepository roles;

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
	    throw new BadCredentialsException(
		    "This email is already used");
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
	TokenInfo tokenInfo = new TokenInfo();
	List<String> roles = new ArrayList<String>();
	String role = candidate.getRole().getName();
	roles.add(role);
	String token = authHelper.createJWT(roles, "roles");
	tokenInfo.setToken(token);
	tokenInfo.setRole(role);
	return tokenInfo;
    }

}
