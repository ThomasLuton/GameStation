package co.simplon.game.services;

import java.util.Collection;

import co.simplon.game.dtos.game.GameDetailView;
import co.simplon.game.dtos.game.GameLightView;
import co.simplon.game.dtos.game.UpdateGame;

public interface GameService {

    public GameDetailView getGameDetailById(Long id);

    public Collection<GameLightView> getAllGames();

    public void deleteById(Long id);

    public void updateById(Long id, UpdateGame inputs);

}
