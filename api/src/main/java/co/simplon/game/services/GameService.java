package co.simplon.game.services;

import java.util.Collection;

import co.simplon.game.dtos.game.GameDetailView;
import co.simplon.game.dtos.game.GameLightView;

public interface GameService {

    public GameDetailView getGameDetailById(Long id);

    public Collection<GameLightView> getAllGames();

}
