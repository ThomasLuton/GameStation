package co.simplon.game.dtos.authentication;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class CreateUserAccount {

    @NotNull
    @Email
    private String email;
    @NotNull
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[%||!||*])(?!.* ).{8,42}")
    private String password;
    @NotNull
    @Length(min = 4, max = 20)
    private String nickname;

    public CreateUserAccount() {
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
