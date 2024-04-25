package co.simplon.game.sessions.services;

import java.util.List;

import co.simplon.game.players.entities.Player;
import co.simplon.game.sessions.entities.GamePlayed;
import co.simplon.game.sessions.entities.Session;

public interface GamePlayedService {

    public void create(Player player, Session Session);

    public List<GamePlayed> getNumberOfPlayer(
	    Session session);

}
