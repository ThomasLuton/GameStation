package co.simplon.game.players.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.game.players.PlayerService;
import co.simplon.game.players.dtos.PlayerSimpleView;
import co.simplon.game.players.dtos.SignIn;
import co.simplon.game.players.dtos.SignUp;
import co.simplon.game.players.dtos.TokenInfo;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService service;

    public PlayerController(PlayerService service) {
	this.service = service;
    }

    @PostMapping("/sign-up")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void signUp(@RequestBody @Valid SignUp inputs) {
	service.createPlayer(inputs);
    }

    @PostMapping("/sign-in")
    public TokenInfo signIn(
	    @RequestBody SignIn credentials) {
	return service.connectPlayer(credentials);
    }

    @GetMapping("/connected")
    public List<PlayerSimpleView> getConnectedPlayer() {
	return service.getConnectedUsers();
    }

    @GetMapping
    public List<PlayerSimpleView> getAllPlayer() {
	return service.getAllUsers();
    }

}
