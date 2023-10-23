package co.simplon.game.controllers;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import co.simplon.game.dtos.user.UserConnection;
import co.simplon.game.dtos.user.UserView;
import co.simplon.game.entities.user.ConnectedUsers;
import co.simplon.game.repositories.UserRepository;
import co.simplon.game.services.UserService;

@Controller
public class UserWebSocketController {

    private final UserService userService;
    private final UserRepository users;
    private final ConnectedUsers connectedUsers;

    public UserWebSocketController(UserService userService,
	    UserRepository users,
	    ConnectedUsers connectedUsers) {
	this.userService = userService;
	this.users = users;
	this.connectedUsers = connectedUsers;
    }

    @MessageMapping("/connect")
    @SendTo("/topic/users")
    public List<UserView> connect(UserConnection user,
	    SimpMessageHeaderAccessor headerAccessor) {
	headerAccessor.getSessionAttributes()
		.put("username", user.getNickname());
	this.connectedUsers.getUsers()
		.add(users.findOneByEmailOrNickname(
			user.getNickname(),
			user.getNickname()));
	return userService.getConnectedUsers();
    }
}
