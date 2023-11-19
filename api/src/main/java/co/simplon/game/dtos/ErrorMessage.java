package co.simplon.game.dtos;

public class ErrorMessage {

    private String message;
    private String codeError;

    public ErrorMessage() {
	super();
	// TODO Auto-generated constructor stub
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public String getCodeError() {
	return codeError;
    }

    public void setCodeError(String codeError) {
	this.codeError = codeError;
    }

    @Override
    public String toString() {
	return "{message=" + message + ", codeError="
		+ codeError + "}";
    }

}
