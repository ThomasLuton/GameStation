package co.simplon.game.players.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.game.players.dtos.SignIn;
import co.simplon.game.players.dtos.SignUp;
import co.simplon.game.players.dtos.TokenInfo;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @PostMapping("/sign-up")
    public void signUp(@RequestBody @Valid SignUp inputs) {
	System.out.println(
		inputs.email() + " " + inputs.playerName()
			+ " " + inputs.password());
    }

    @PostMapping("/sign-in")
    public TokenInfo signIn(
	    @RequestBody SignIn credentials) {
	System.out.println(credentials.email() + " "
		+ credentials.password());
	return null;
    }

}
