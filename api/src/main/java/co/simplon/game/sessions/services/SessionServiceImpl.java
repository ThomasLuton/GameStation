package co.simplon.game.sessions.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.game.errors.CodeError;
import co.simplon.game.errors.GameStationError;
import co.simplon.game.games.entities.Game;
import co.simplon.game.games.repositories.GameRepository;
import co.simplon.game.players.entities.Player;
import co.simplon.game.players.repositories.PlayerRepository;
import co.simplon.game.sessions.dtos.SessionCreated;
import co.simplon.game.sessions.entities.Session;
import co.simplon.game.sessions.entities.SessionPlayer;
import co.simplon.game.sessions.enums.Step;
import co.simplon.game.sessions.repositories.SessionRepository;

@Service
@Transactional(readOnly = true)
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessions;
    private final PlayerRepository players;
    private final GameRepository games;
    private final SessionPlayerService sessionPlayerService;

    public SessionServiceImpl(SessionRepository sessions,
	    PlayerRepository players, GameRepository games,
	    SessionPlayerService sessionPlayerService) {
	this.sessions = sessions;
	this.players = players;
	this.games = games;
	this.sessionPlayerService = sessionPlayerService;
    }

    @Override
    @Transactional
    public SessionCreated createSession(
	    Integer creatorSuffix, Long gameId) {
	Game game = games.findById(gameId)
		.orElseThrow(() -> new GameStationError(
			CodeError.NoGameFound,
			"Game not found",
			HttpStatus.BAD_REQUEST));
	Player creator = players
		.findOneByGamerTagSuffix(creatorSuffix);
	if (creator == null) {
	    throw new GameStationError(
		    CodeError.UnknownPlayer,
		    "Player not found",
		    HttpStatus.BAD_REQUEST);
	}
	checkIfPlayerIsInGame(creator);
	Session session = new Session();
	String sessionCode = generateRandomSessionCode();
	session.setSessionCode(sessionCode);
	session.setCreator(creator);
	session.setGame(game);
	session.setStep(Step.DRAFT.getNumber());
	session = sessions.save(session);
	sessionPlayerService.create(creator, session);
	return new SessionCreated(sessionCode, Step.DRAFT);
    }

    @Override
    @Transactional
    public void startGame(Integer creatorSuffix,
	    String sessionCode) {
	Player creator = players
		.findOneByGamerTagSuffix(creatorSuffix);
	if (creator == null) {
	    throw new GameStationError(
		    CodeError.UnknownPlayer,
		    "Player not found",
		    HttpStatus.BAD_REQUEST);
	}
	Session session = sessions
		.findBySessionCode(sessionCode)
		.orElseThrow(() -> new GameStationError(
			CodeError.SessionNotFound,
			"Session not found",
			HttpStatus.BAD_REQUEST));
	checkIfSessionIsNotStarted(session);
	if (!session.getCreator().equals(creator)) {
	    throw new GameStationError(
		    CodeError.PlayerIsNotCreator,
		    "The player is not the creator of this session",
		    HttpStatus.BAD_REQUEST);
	}
	checkNumberOfPlayer(session, session.getGame());
	session.setStep(Step.STARTED.getNumber());
	sessions.save(session);
    }

    private void checkNumberOfPlayer(Session session,
	    Game game) {
	List<SessionPlayer> listOfPlayer = sessionPlayerService
		.getNumberOfPlayer(session);
	int size = listOfPlayer.size();
	if ((size < game.getMinPlayer())
		|| (size > game.getMaxPlayer())) {
	    throw new GameStationError(
		    CodeError.NumberOfPlayer,
		    "Number of player incorrect",
		    HttpStatus.BAD_REQUEST);
	}

    }

    private void checkIfPlayerIsInGame(Player player) {
	boolean check = sessions
		.countSessionNotFinishForOnePlayer(
			player.getId()) != 0;
	if (check) {
	    throw new GameStationError(
		    CodeError.PlayerAlreadyInGame,
		    "Player already in game",
		    HttpStatus.BAD_REQUEST);
	}
    }

    private String generateRandomSessionCode() {
	UUID uuid = UUID.randomUUID();
	String uuidString = uuid.toString().replaceAll("-",
		"");
	return uuidString.substring(0, 7);
    }

    @Override
    @Transactional
    public void joinSession(Integer playerSuffix,
	    String sessionCode) {
	Session session = sessions
		.findBySessionCode(sessionCode)
		.orElseThrow(() -> new GameStationError(
			CodeError.SessionNotFound,
			"Session not found",
			HttpStatus.BAD_REQUEST));
	Player joiner = players
		.findOneByGamerTagSuffix(playerSuffix);
	if (joiner == null) {
	    throw new GameStationError(
		    CodeError.UnknownPlayer,
		    "Player not found",
		    HttpStatus.BAD_REQUEST);
	}
	checkIfPlayerIsInGame(joiner);
	checkIfSessionIsNotStarted(session);
	sessionPlayerService.create(joiner, session);
    }

    private void checkIfSessionIsNotStarted(
	    Session session) {
	if (session.getStep() != Step.DRAFT.getNumber()) {
	    throw new GameStationError(
		    CodeError.SessionAlreadyStarted,
		    "Session already started",
		    HttpStatus.BAD_REQUEST);
	}
    }

    @Override
    public void finishGame(String sessionCode) {
	// appelé à la fin d'un jeu
	// session step => finish
	// for each gamePlayed => addResult
    }

    @Override
    @Transactional
    public void leaveSession(Integer playerSuffix) {
	Player leaver = players
		.findOneByGamerTagSuffix(playerSuffix);
	if (leaver == null) {
	    throw new GameStationError(
		    CodeError.UnknownPlayer,
		    "Player not found",
		    HttpStatus.BAD_REQUEST);
	}
	boolean check = sessions
		.countSessionNotFinishForOnePlayer(
			leaver.getId()) == 0;
	if (check) {
	    throw new GameStationError(
		    CodeError.PlayerNotInGame,
		    "Player not in game",
		    HttpStatus.BAD_REQUEST);
	}
	sessionPlayerService.delete(leaver);
    }

    @Override
    public SessionCreated getSessionInDraft(
	    Integer playerSuffix) {
	Player player = players
		.findOneByGamerTagSuffix(playerSuffix);
	Session session = sessions
		.getSessionInDraftForOnePlayer(
			player.getId());
	if (session == null) {
	    return null;
	}
	return new SessionCreated(session.getSessionCode(),
		Step.DRAFT);
    }

}
