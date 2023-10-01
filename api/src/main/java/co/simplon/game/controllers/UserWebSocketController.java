package co.simplon.game.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import co.simplon.game.dtos.user.UserConnection;

@Controller
public class UserWebSocketController {

    public UserWebSocketController() {
    }

    @MessageMapping("/connect")
    @SendTo("/topic/users")
    public String connect(UserConnection user,
	    SimpMessageHeaderAccessor headerAccessor) {
	headerAccessor.getSessionAttributes()
		.put("username", user.getNickname());
	return "New connection: " + user.getNickname();
    }
}
