package co.simplon.game.favorites.dtos;

import co.simplon.game.games.dtos.GameLightView;
import co.simplon.game.players.dtos.PlayerSimpleView;

public interface FavoriteView {

    Long getId();

    PlayerSimpleView getPlayer();

    GameLightView getGame();
}
