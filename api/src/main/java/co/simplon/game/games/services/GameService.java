package co.simplon.game.games.services;

import java.util.List;

import co.simplon.game.games.dtos.GameDetailView;
import co.simplon.game.games.dtos.GameLightView;

public interface GameService {

    List<GameLightView> getAllGames();

    GameDetailView getOneGameById(Long id);

}
