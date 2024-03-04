package co.simplon.game.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorExceptionHandler
	extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = GameStationError.class)
    protected ResponseEntity<Object> handleGameStationError(
	    GameStationError ex) {
	return handleExceptionInternal(ex,
		ex.getErrorMessage(), new HttpHeaders(),
		ex.getStatus(), null);
    }
}
