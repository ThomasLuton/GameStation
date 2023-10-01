package co.simplon.game.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class UserWebSocketListener {

    @Autowired
    private SimpMessagingTemplate messageTemplate;

    @EventListener
    public void handleWebSocketDisconnectListener(
	    SessionDisconnectEvent event) {
	StompHeaderAccessor headerAccessor = StompHeaderAccessor
		.wrap(event.getMessage());
	String username = (String) headerAccessor
		.getSessionAttributes().get("username");

	if (username != null) {
	    String message = username
		    + " has been deconnected";
	    // Methode la plus importante
	    // Envoie un message sur un lien ws
	    messageTemplate.convertAndSend("/topic/users",
		    message);
	}
    }
}
