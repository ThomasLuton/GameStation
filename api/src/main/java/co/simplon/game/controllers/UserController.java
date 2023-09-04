package co.simplon.game.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.game.dtos.authentication.CreateUserAccount;
import co.simplon.game.dtos.authentication.Credentials;
import co.simplon.game.dtos.authentication.TokenInfo;
import co.simplon.game.services.UserService;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
	this.userService = userService;
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void signUp(
	    @RequestBody @Valid CreateUserAccount inputs) {
	userService.signUp(inputs);
    }

    @PostMapping("/sign-in")
    public TokenInfo signIn(
	    @RequestBody Credentials inputs) {
	return userService.signIn(inputs);
    }

    @GetMapping("/test")
    public String test() {
	return "Works";
    }

}
