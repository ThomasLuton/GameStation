package co.simplon.game.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.game.dtos.user.CreateUserAccount;
import co.simplon.game.dtos.user.Credentials;
import co.simplon.game.dtos.user.TokenInfo;
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

    // Test du proto store à changer!!
    @GetMapping("/test")
    public List<String> test() {
	return userService.getConnectedUsers();
    }

}
