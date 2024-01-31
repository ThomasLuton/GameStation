package co.simplon.game.controllers;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.game.dtos.game.GameDetailView;
import co.simplon.game.dtos.game.GameLightView;
import co.simplon.game.dtos.game.UpdateGame;
import co.simplon.game.services.GameService;
import jakarta.validation.Valid;

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

    @PreAuthorize("hasRole('ROLE_locked')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
	gameService.deleteById(id);
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void uptadeById(@PathVariable("id") Long id,
	    @RequestBody @Valid UpdateGame inputs) {
	gameService.updateById(id, inputs);
    }
}
