package co.simplon.game.players.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.game.players.FriendService;
import co.simplon.game.players.dtos.GamerTagDto;
import co.simplon.game.utils.AuthHelper;

@RestController
@RequestMapping("/friend")
public class FriendController {

    private final FriendService service;
    private final AuthHelper authHelper;

    public FriendController(FriendService service,
	    AuthHelper authHelper) {
	this.service = service;
	this.authHelper = authHelper;
    }

    @PostMapping("/request")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void requestFriend(
	    @RequestBody GamerTagDto newFriend) {
	service.request(newFriend);
    }

    @PostMapping("/accept")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void acceptFriend(
	    @RequestBody GamerTagDto newFriend,
	    JwtAuthenticationToken principal) {
	Map<String, Object> user = authHelper
		.getPrincipalInfo(principal);
	Long suffix = (Long) user.get("suffix");
	service.accept(newFriend, suffix.intValue());
    }

    @PostMapping("/refuse")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void refuseFriend(
	    @RequestBody GamerTagDto newFriend) {
	service.refuse(newFriend);
    }

    @PostMapping("/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteFriend(
	    @RequestBody GamerTagDto oldFriend,
	    JwtAuthenticationToken principal) {
	Map<String, Object> user = authHelper
		.getPrincipalInfo(principal);
	Long suffix = (Long) user.get("suffix");
	service.delete(oldFriend, suffix.intValue());
    }

}
