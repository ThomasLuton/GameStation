package co.simplon.game.sessions.services;

import co.simplon.game.sessions.dtos.SessionCreated;

public interface SessionService {

    public SessionCreated createSession(
	    Integer creatorSuffix, Long gameId);

    public void joinSession(Integer playerSuffix,
	    Integer sessionNumber);

    public void startGame(Integer creatorSuffix,
	    Integer sessionNumber);

    public void finishGame(Integer sessionNumber);
}
