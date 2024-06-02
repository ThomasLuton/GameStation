package co.simplon.game.sessions.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.game.errors.CodeError;
import co.simplon.game.errors.GameStationError;
import co.simplon.game.players.entities.Player;
import co.simplon.game.sessions.entities.Result;
import co.simplon.game.sessions.entities.Session;
import co.simplon.game.sessions.entities.SessionPlayer;
import co.simplon.game.sessions.repositories.SessionPlayerRepository;

@Service
@Transactional(readOnly = true)
public class SessionPlayerServiceImpl
	implements SessionPlayerService {

    private SessionPlayerRepository gamesPlayed;

    public SessionPlayerServiceImpl(
	    SessionPlayerRepository gamesPlayed) {
	this.gamesPlayed = gamesPlayed;
    }

    @Override
    @Transactional
    public void create(Player player, Session session) {
	SessionPlayer sessionPlayer = new SessionPlayer();
	sessionPlayer.setPlayer(player);
	sessionPlayer.setSession(session);
	gamesPlayed.save(sessionPlayer);
    }

    @Override
    public List<SessionPlayer> getNumberOfPlayer(
	    Session session) {
	return gamesPlayed.findAllBySession(session);
    }

    @Override
    public void addResult(Result result) {
	// TODO Auto-generated method stub
    }

    @Override
    public List<SessionPlayer> getGamePlayedByPlayer(
	    Player player) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    @Transactional
    public void delete(Player leaver) {
	SessionPlayer sessionPlayer = gamesPlayed
		.findOneByPlayer(leaver).get();
	if (sessionPlayer.getResult() != null) {
	    throw new GameStationError(
		    CodeError.CantLeaveFinishSession,
		    "Can't leave finish session",
		    HttpStatus.BAD_REQUEST);
	}
	gamesPlayed.delete(sessionPlayer);
    }
}
