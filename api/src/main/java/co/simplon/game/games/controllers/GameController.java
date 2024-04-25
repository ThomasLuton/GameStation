package co.simplon.game.games.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.game.games.dtos.GameDetailView;
import co.simplon.game.games.dtos.GameLightView;
import co.simplon.game.games.services.GameService;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
	this.gameService = gameService;
    }

    @GetMapping()
    public List<GameLightView> getAllGames() {
	return gameService.getAllGames();
    }

    @GetMapping("/{id}")
    public GameDetailView getOneGameById(
	    @PathVariable Long id) {
	return gameService.getOneGameById(id);
    }
}
