package co.simplon.game.controllers;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import co.simplon.game.dtos.user.PlayerConnection;
import co.simplon.game.dtos.user.PlayerView;
import co.simplon.game.entities.user.ConnectedPlayers;
import co.simplon.game.repositories.PlayerRepository;
import co.simplon.game.services.PlayerService;

@Controller
public class PlayerWebSocketController {

    private final PlayerService playerService;
    private final PlayerRepository users;
    private final ConnectedPlayers connectedPlayers;

    public PlayerWebSocketController(PlayerService playerService,
	    PlayerRepository users,
	    ConnectedPlayers connectedPlayers) {
	this.playerService = playerService;
	this.users = users;
	this.connectedPlayers = connectedPlayers;
    }

    @MessageMapping("/connect")
    @SendTo("/topic/users")
    public List<PlayerView> connect(PlayerConnection user,
	    SimpMessageHeaderAccessor headerAccessor) {
	headerAccessor.getSessionAttributes()
		.put("username", user.getNickname());
	this.connectedPlayers.getUsers().add(users
		.findOneByNickname(user.getNickname()));
	return playerService.getConnectedUsers();
    }
}
