package co.simplon.game.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import co.simplon.game.dtos.user.UserView;
import co.simplon.game.services.UserService;

@Component
public class UserWebSocketListener {

    @Autowired
    private SimpMessagingTemplate messageTemplate;

    @Autowired
    private UserService userService;

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
	    userService.logOut(username);
	    List<UserView> message = userService
		    .getConnectedUsers();
	    messageTemplate.convertAndSend("/topic/users",
		    message);
	}
    }
}
