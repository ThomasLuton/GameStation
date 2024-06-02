package co.simplon.game.sessions.services;

import co.simplon.game.sessions.dtos.SessionCreated;

public interface SessionService {

    public SessionCreated createSession(
	    Integer creatorSuffix, Long gameId);

    public void joinSession(Integer playerSuffix,
	    String sessionCode);

    public void startGame(Integer creatorSuffix,
	    String sessionCode);

    public void finishGame(String sessionCode);

    public void leaveSession(Integer playerSuffix);

    public SessionCreated getSessionInDraft(
	    Integer playerSuffix);
}
