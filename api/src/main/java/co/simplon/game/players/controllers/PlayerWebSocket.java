package co.simplon.game.players.controllers;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import co.simplon.game.players.PlayerService;
import co.simplon.game.players.dtos.ConnectDto;
import co.simplon.game.players.dtos.PlayerSimpleView;

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
	playerService.logIn(player.playerEmail());
	headerAccessor.getSessionAttributes().put("player",
		player.playerEmail());
	return playerService.getConnectedUsers();
    }

}