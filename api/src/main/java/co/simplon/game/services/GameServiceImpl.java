package co.simplon.game.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import co.simplon.game.dtos.game.GameDetailView;
import co.simplon.game.dtos.game.GameLightView;
import co.simplon.game.repositories.GameRepository;

@Service
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

}
