package co.simplon.game.configurations;

import org.springframework.security.core.AuthenticationException;

public class CredentialAlreadyUseException
	extends AuthenticationException {

    public CredentialAlreadyUseException(String msg) {
	super(msg);
    }

    public CredentialAlreadyUseException(String msg,
	    Throwable cause) {
	super(msg, cause);
    }

}
