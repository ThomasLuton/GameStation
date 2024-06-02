package co.simplon.game.sessions.services;

import java.util.List;

import co.simplon.game.players.entities.Player;
import co.simplon.game.sessions.entities.Result;
import co.simplon.game.sessions.entities.Session;
import co.simplon.game.sessions.entities.SessionPlayer;

public interface SessionPlayerService {

    public void create(Player player, Session Session);

    public List<SessionPlayer> getNumberOfPlayer(
	    Session session);

    public void addResult(Result result);

    public List<SessionPlayer> getGamePlayedByPlayer(
	    Player player);

    public void delete(Player leaver);
}
