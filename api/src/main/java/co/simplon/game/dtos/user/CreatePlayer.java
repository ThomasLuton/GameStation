package co.simplon.game.dtos.user;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreatePlayer {

    @NotNull
    @Email
    private String email;
    @NotNull
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[%||!||*])(?!.* ).{8,42}")
    private String password;
    @NotNull
    @Length(min = 4, max = 20)
    private String nickname;

    public CreatePlayer() {
	super();
	// TODO Auto-generated constructor stub
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getNickname() {
	return nickname;
    }

    public void setNickname(String nickname) {
	this.nickname = nickname;
    }

    @Override
    public String toString() {
	return "{email=" + email + ", password=" + password
		+ ", nickname=" + nickname + "}";
    }

}
