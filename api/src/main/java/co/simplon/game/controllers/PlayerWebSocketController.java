package co.simplon.game.controllers;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import co.simplon.game.dtos.user.PlayerConnection;
import co.simplon.game.dtos.user.PlayerView;
import co.simplon.game.repositories.PlayerRepository;
import co.simplon.game.services.PlayerService;

@Controller
public class PlayerWebSocketController {

    private final PlayerService playerService;
    private final PlayerRepository users;

    public PlayerWebSocketController(
	    PlayerService playerService,
	    PlayerRepository users) {
	this.playerService = playerService;
	this.users = users;
    }

    @MessageMapping("/connect")
    @SendTo("/topic/users")
    public List<PlayerView> connect(PlayerConnection user,
	    SimpMessageHeaderAccessor headerAccessor) {
	playerService.logIn(user.getNickname());
	headerAccessor.getSessionAttributes()
		.put("username", user.getNickname());
	return playerService.getConnectedUsers();
    }
}
