package co.simplon.game.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.game.dtos.user.CreatePlayer;
import co.simplon.game.dtos.user.Credentials;
import co.simplon.game.dtos.user.PlayerDetailView;
import co.simplon.game.dtos.user.PlayerView;
import co.simplon.game.dtos.user.TokenInfo;
import co.simplon.game.services.PlayerService;

@RestController
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
	this.playerService = playerService;
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void signUp(
	    @RequestBody @Valid CreatePlayer inputs) {
	playerService.signUp(inputs);
    }

    @PostMapping("/sign-in")
    public TokenInfo signIn(
	    @RequestBody Credentials inputs) {
	return playerService.signIn(inputs);
    }

    @GetMapping("/refresh")
    public String refresh() {
	return null;
    }

    // voir si ce end point est tjrs necessaire
    @GetMapping("/users")
    public List<PlayerDetailView> getAllUsers() {
	return playerService.getAllUsers();
    }

    @GetMapping("/connectedUsers")
    public List<PlayerView> getAllConnectedUsers() {
	return playerService.getConnectedUsers();
    }

}
