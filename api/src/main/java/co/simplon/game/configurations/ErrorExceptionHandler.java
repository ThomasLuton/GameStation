package co.simplon.game.configurations;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorExceptionHandler
	extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BadCredentialsException.class)
    protected ResponseEntity<Object> handleBadCredentialException(
	    BadCredentialsException ex) {
	// dto
	String body = ex.getMessage();
	return handleExceptionInternal(ex, body,
		new HttpHeaders(), HttpStatus.UNAUTHORIZED,
		null);
    }

    @ExceptionHandler(value = CredentialAlreadyUseException.class)
    protected ResponseEntity<Object> handleCredentialAlreadyUseException(
	    CredentialAlreadyUseException ex) {
	String body = ex.getMessage();
	return handleExceptionInternal(ex, body,
		new HttpHeaders(), HttpStatus.BAD_REQUEST,
		null);
    }
}
