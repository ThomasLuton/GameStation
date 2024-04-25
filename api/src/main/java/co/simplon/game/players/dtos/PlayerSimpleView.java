package co.simplon.game.players.dtos;

import co.simplon.game.players.entities.GamerTag;

public interface PlayerSimpleView {

    GamerTag getGamerTag();

    String getAvatar();

    boolean isConnection();

}
