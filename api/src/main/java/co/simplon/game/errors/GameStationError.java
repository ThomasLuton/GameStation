package co.simplon.game.errors;

import org.springframework.http.HttpStatus;

public class GameStationError extends RuntimeException {

    private final ErrorMessage errorMessage;
    private final HttpStatus status;

    public GameStationError(CodeError code, String msg,
	    HttpStatus status) {
	this.errorMessage = new ErrorMessage(code, msg);
	this.status = status;
    }

    public ErrorMessage getErrorMessage() {
	return errorMessage;
    }

    public HttpStatus getStatus() {
	return status;
    }

}
