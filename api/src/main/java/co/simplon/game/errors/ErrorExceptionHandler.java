package co.simplon.game.errors;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorExceptionHandler
	extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = GameStationError.class)
    protected ResponseEntity<Object> handleGameStationError(
	    GameStationError ex) {
	return handleExceptionInternal(ex,
		ex.getErrorMessage(), new HttpHeaders(),
		ex.getStatus(), null);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolationException(
	    DataIntegrityViolationException ex,
	    WebRequest request) {
	return handleExceptionInternal(ex, null,
		new HttpHeaders(), HttpStatus.CONFLICT,
		request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(
	    ResourceNotFoundException ex,
	    WebRequest request) {
	// custom body
	// request.getContextPath()
	return handleExceptionInternal(ex, null,
		new HttpHeaders(), HttpStatus.NOT_FOUND,
		request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
	    MethodArgumentNotValidException ex,
	    HttpHeaders headers, HttpStatusCode status,
	    WebRequest request) {
	ValidationErrors errors = new ValidationErrors();
	List<FieldError> fieldErrors = ex.getFieldErrors();
	fieldErrors.forEach((e) -> errors
		.addFieldError(e.getField(), e.getCode()));
	//
	List<ObjectError> globalErrors = ex
		.getGlobalErrors();
	globalErrors.forEach(
		(e) -> errors.addGlobalError(e.getCode()));
	//
	return handleExceptionInternal(ex, errors, headers,
		status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
	    Exception ex, Object body, HttpHeaders headers,
	    HttpStatusCode statusCode, WebRequest request) {
	if (logger.isDebugEnabled()) {
	    logger.debug("A MassiException occured mate!",
		    ex);
	}
	return super.handleExceptionInternal(ex, body,
		headers, statusCode, request);
    }
}
