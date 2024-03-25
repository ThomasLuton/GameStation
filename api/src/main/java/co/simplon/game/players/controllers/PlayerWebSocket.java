package co.simplon.game.players.controllers;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import co.simplon.game.players.dtos.ConnectDto;
import co.simplon.game.players.dtos.PlayerSimpleView;
import co.simplon.game.players.services.PlayerService;

@Controller
public class PlayerWebSocket {

    private final PlayerService playerService;

    public PlayerWebSocket(PlayerService playerService) {
	this.playerService = playerService;
    }

    @MessageMapping("/connect")
    @SendTo("/topic/users")
    public List<PlayerSimpleView> connect(ConnectDto player,
	    SimpMessageHeaderAccessor headerAccessor) {
	// playerService.logIn(player.gamerTag());
	headerAccessor.getSessionAttributes().put("player",
		player.gamerTag());
	return playerService.getConnectedUsers();
    }

}
