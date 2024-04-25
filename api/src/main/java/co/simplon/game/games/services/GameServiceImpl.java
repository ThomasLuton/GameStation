package co.simplon.game.games.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.game.games.dtos.GameDetailView;
import co.simplon.game.games.dtos.GameLightView;
import co.simplon.game.games.repositories.GameRepository;

@Service
@Transactional(readOnly = true)
public class GameServiceImpl implements GameService {

    private final GameRepository games;

    public GameServiceImpl(GameRepository games) {
	this.games = games;
    }

    @Override
    public List<GameLightView> getAllGames() {
	return games.findAllProjectedByOrderById();
    }

    @Override
    public GameDetailView getOneGameById(Long id) {
	return games.findOneById(id);
    }

}
