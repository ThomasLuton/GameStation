package co.simplon.game.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.game.dtos.game.GameDetailView;
import co.simplon.game.dtos.game.GameLightView;
import co.simplon.game.services.GameService;

@RestController
@RequestMapping("/game")
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
	this.gameService = gameService;
    }

    @GetMapping
    public Collection<GameLightView> getAllGames() {
	return gameService.getAllGames();
    }

    @GetMapping("/{id}")
    public GameDetailView getOneGameById(
	    @PathVariable("id") Long id) {
	return gameService.getGameDetailById(id);
    }

}
