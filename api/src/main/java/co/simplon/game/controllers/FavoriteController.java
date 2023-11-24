package co.simplon.game.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.game.dtos.favorite.FavoriteView;
import co.simplon.game.services.FavoriteService;
import co.simplon.game.utils.AuthHelper;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final AuthHelper authHelper;

    public FavoriteController(FavoriteService service,
	    AuthHelper authHelper) {
	this.favoriteService = service;
	this.authHelper = authHelper;
    }

    @PostMapping("/{user}/{game}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void add(@PathVariable("user") Long userID,
	    @PathVariable("game") Long gameID) {
	favoriteService.add(userID, gameID);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(
	    @PathVariable("id") Long favoriteID) {
	favoriteService.remove(favoriteID);
    }

    @GetMapping("/{id}")
    public List<FavoriteView> allForOnePlayer(
	    @PathVariable("id") Long playerID) {
	return favoriteService
		.getAllFavoriteForOnePlayer(playerID);
    }

    @PatchMapping("/{id}")
    public Long patch(@PathVariable("id") Long gameID,
	    JwtAuthenticationToken principal) {
	Map<String, Object> test = authHelper
		.getPrincipalInfo(principal);
	String playerName = (String) test.get("name");
	return favoriteService.patch(playerName, gameID);
    }

}
