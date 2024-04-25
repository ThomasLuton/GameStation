package co.simplon.game.sessions.services;

import co.simplon.game.players.entities.Player;
import co.simplon.game.sessions.entities.Session;

public interface GamePlayedService {

    public void create(Player player, Session Session);

}
