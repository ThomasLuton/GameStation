package co.simplon.game.sessions.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.game.sessions.dtos.SessionCreated;
import co.simplon.game.sessions.services.SessionService;
import co.simplon.game.utils.AuthHelper;

@RestController
@RequestMapping("sessions")
public class SessionController {

    private final SessionService service;
    private final AuthHelper authHelper;

    public SessionController(SessionService service,
	    AuthHelper authHelper) {
	this.service = service;
	this.authHelper = authHelper;
    }

    @PostMapping("/create/{game}")
    public SessionCreated createSession(
	    @PathVariable("game") Long gameId,
	    JwtAuthenticationToken principal) {
	Map<String, Object> user = authHelper
		.getPrincipalInfo(principal);
	Long suffix = (Long) user.get("suffix");
	return service.createSession(suffix.intValue(),
		gameId);
    }

    @PatchMapping("/start/{session}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void startGame(
	    @PathVariable("session") String sessionCode,
	    JwtAuthenticationToken principal) {
	Map<String, Object> user = authHelper
		.getPrincipalInfo(principal);
	Long suffix = (Long) user.get("suffix");
	service.startGame(suffix.intValue(), sessionCode);
    }

    @PostMapping("/join/{session}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void joinGame(
	    @PathVariable("session") String sessionCode,
	    JwtAuthenticationToken principal) {
	Map<String, Object> user = authHelper
		.getPrincipalInfo(principal);
	Long suffix = (Long) user.get("suffix");
	service.joinSession(suffix.intValue(), sessionCode);
    }
}
