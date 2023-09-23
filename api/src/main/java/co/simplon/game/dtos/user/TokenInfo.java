package co.simplon.game.dtos.user;

public class TokenInfo {

    private String token;
    private String role;
    private String subject;

    public TokenInfo() {
    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    @Override
    public String toString() {
	return "{token=" + token + ", role=" + role
		+ ", subject=" + subject + "}";
    }

}
