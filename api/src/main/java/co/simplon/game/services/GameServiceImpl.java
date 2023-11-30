package co.simplon.game.services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.game.dtos.game.GameDetailView;
import co.simplon.game.dtos.game.GameLightView;
import co.simplon.game.dtos.game.UpdateGame;
import co.simplon.game.entities.game.Game;
import co.simplon.game.repositories.GameRepository;

@Service
@Transactional(readOnly = true)
public class GameServiceImpl implements GameService {

    private GameRepository games;

    public GameServiceImpl(GameRepository games) {
	this.games = games;
    }

    @Override
    public GameDetailView getGameDetailById(Long id) {
	return games.findOneProjectById(id);
    }

    @Override
    public Collection<GameLightView> getAllGames() {
	return games.findAllProjectedByOrderById();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
	games.deleteById(id);
    }

    @Override
    @Transactional
    public void updateById(Long id, UpdateGame inputs) {
	Game game = games.findOneProjectedById(id);
	if (game == null) {
	    throw new Error("Game not found");
	}
	game.setGameName(inputs.getGameName());
	game.setGameDetail(inputs.getGameDetail());
	game.setAvailable(inputs.isAvailable());
	game.setMinPlayer(inputs.getMinPlayer());
	game.setMaxPlayer(inputs.getMaxPlayer());
	game.setVictoryPoint(inputs.getVictoryPoint());
	game.setDrawPoint(inputs.getDrawPoint());
	game.setLosePoint(inputs.getLosePoint());

	games.save(game);
    }

}
