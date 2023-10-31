package co.simplon.game.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import co.simplon.game.dtos.user.PlayerView;
import co.simplon.game.services.PlayerService;

@Component
public class PlayerWebSocketListener {

    @Autowired
    private SimpMessagingTemplate messageTemplate;

    @Autowired
    private PlayerService playerService;

    @EventListener
    public void handleWebSocketDisconnectListener(
	    SessionDisconnectEvent event) {
	StompHeaderAccessor headerAccessor = StompHeaderAccessor
		.wrap(event.getMessage());
	String username = (String) headerAccessor
		.getSessionAttributes().get("username");
	if (username != null) {
	    // Methode la plus importante
	    // Envoie un message sur un lien ws
	    playerService.logOut(username);
	    List<PlayerView> message = playerService
		    .getConnectedUsers();
	    messageTemplate.convertAndSend("/topic/users",
		    message);
	    // messageTemplate.convertAndSend("/topic/users",
	    // message);
	}
    }
}
