package co.simplon.game.players.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.game.players.dtos.PlayerOptionsView;
import co.simplon.game.players.dtos.PlayerSimpleView;
import co.simplon.game.players.dtos.PlayerUpdateAvatar;
import co.simplon.game.players.dtos.PlayerUpdateNotification;
import co.simplon.game.players.dtos.PlayerUpdatePlayerName;
import co.simplon.game.players.dtos.SignIn;
import co.simplon.game.players.dtos.SignUp;
import co.simplon.game.players.dtos.TokenInfo;
import co.simplon.game.players.services.PlayerService;
import co.simplon.game.utils.AuthHelper;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService service;
    private final AuthHelper authHelper;

    public PlayerController(PlayerService service,
	    AuthHelper authHelper) {
	this.service = service;
	this.authHelper = authHelper;
    }

    @GetMapping("/options")
    public PlayerOptionsView getUserForOptions(
	    JwtAuthenticationToken principal) {
	Long suffix = authHelper.getSuffix(principal);
	return service.getOneForOptions(suffix.intValue());
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

    @PostMapping("/log-out")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void logOut(JwtAuthenticationToken principal) {
	Long suffix = authHelper.getSuffix(principal);
	service.logOut(suffix.intValue());
    }

    @GetMapping("/connected")
    public List<PlayerSimpleView> getConnectedPlayer() {
	return service.getConnectedUsers();
    }

    @GetMapping
    public List<PlayerSimpleView> getAllPlayer() {
	return service.getAllUsers();
    }

    @PatchMapping("/update/notification")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateNotification(
	    @Valid @RequestBody PlayerUpdateNotification inputs,
	    JwtAuthenticationToken principal) {
	Long suffix = authHelper.getSuffix(principal);
	service.updatePlayerNotification(inputs,
		suffix.intValue());
    }

    @PatchMapping("/update/player-name")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updatePlayerName(
	    @Valid @RequestBody PlayerUpdatePlayerName inputs,
	    JwtAuthenticationToken principal) {
	Long suffix = authHelper.getSuffix(principal);
	service.updatePlayerName(inputs, suffix.intValue());
    }

    @PatchMapping("/update/avatar")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateAvatar(
	    @ModelAttribute @Valid PlayerUpdateAvatar input,
	    JwtAuthenticationToken principal) {
	Long suffix = authHelper.getSuffix(principal);
	service.updateAvatar(input, suffix.intValue());
    }

}
