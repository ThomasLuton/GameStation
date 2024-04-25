package co.simplon.game.players.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import co.simplon.game.players.dtos.ConnectDto;
import co.simplon.game.players.dtos.PlayerSimpleView;
import co.simplon.game.players.services.PlayerService;

@Controller
public class PlayerWebSocket {

    private final PlayerService playerService;

    @Autowired
    private SimpMessagingTemplate messageTemplate;

    public PlayerWebSocket(PlayerService playerService) {
	this.playerService = playerService;
    }

    @MessageMapping("/connect")
    @SendTo("/topic/users")
    public List<PlayerSimpleView> connect(ConnectDto player,
	    SimpMessageHeaderAccessor headerAccessor) {
	// playerService.logIn(player.gamerTag());
//	headerAccessor.getSessionAttributes().put("player",
//		player.gamerTag());
	return playerService.getConnectedUsers();
    }

    @EventListener
    public void handleWebSocketDisconnectListener(
	    SessionDisconnectEvent event) {
//	StompHeaderAccessor headerAccessor = StompHeaderAccessor
//		.wrap(event.getMessage());
//	String username = (String) headerAccessor
//		.getSessionAttributes().get("player");
//	if (username != null) {
	// Methode la plus importante
	// Envoie un message sur un lien ws
	// playerService.logOut(username);
	List<PlayerSimpleView> message = playerService
		.getConnectedUsers();
	messageTemplate.convertAndSend("/topic/users",
		message);
	// messageTemplate.convertAndSend("/topic/users",
	// message);
	// }
    }

}
