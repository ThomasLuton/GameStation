package co.simplon.game.dtos.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Credentials {

    @NotNull
    @Email
    private String email;
    @NotNull
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[%||!||*])(?!.* ).{8,42}")
    private String password;

    public Credentials() {
    }

    public String getEmail() {
	return email;
    }

    public void setMail(String mail) {
	this.email = mail;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Override
    public String toString() {
	return "{mail=" + email + ", password={protected}}";
    }

}
