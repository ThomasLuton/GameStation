package co.simplon.game.sessions.services;

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
import co.simplon.game.sessions.enums.Step;
import co.simplon.game.sessions.repositories.SessionRepository;

@Service
@Transactional(readOnly = true)
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessions;
    private final PlayerRepository players;
    private final GameRepository games;
    private final GamePlayedService gamePlayedService;

    public SessionServiceImpl(SessionRepository sessions,
	    PlayerRepository players, GameRepository games,
	    GamePlayedService gamePlayedService) {
	this.sessions = sessions;
	this.players = players;
	this.games = games;
	this.gamePlayedService = gamePlayedService;
    }

    @Override
    @Transactional
    public SessionCreated createSession(
	    Integer creatorSuffix, Long gameId) {
	// cas 3 le creator est dans une session qui n'est pas fini
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
	if (isPlayerInGame(creator)) {
	    throw new GameStationError(
		    CodeError.PlayerAlreadyInGame,
		    "Player already in game",
		    HttpStatus.BAD_REQUEST);
	}
	Session session = new Session();
	String sessionCode = generateRandomSessionCode();
	session.setSessionCode(sessionCode);
	session.setCreator(creator);
	session.setGame(game);
	session.setStep(Step.DRAFT.getNumber());
	session = sessions.save(session);
	gamePlayedService.create(creator, session);
	return new SessionCreated(sessionCode, Step.DRAFT);
    }

    @Override
    public void joinSession(Integer playerSuffix,
	    Integer sessionNumber) {
	// TODO Auto-generated method stub

    }

    @Override
    public void startGame(Integer creatorSuffix,
	    Integer sessionNumber) {
	// TODO Auto-generated method stub

    }

    @Override
    public void finishGame(Integer sessionNumber) {
	// TODO Auto-generated method stub

    }

    private boolean isPlayerInGame(Player player) {
	return sessions.countSessionNotFinishForOnePlayer(
		player.getId()) != 0;
    }

    private String generateRandomSessionCode() {
	UUID uuid = UUID.randomUUID();
	String uuidString = uuid.toString().replaceAll("-",
		"");
	return uuidString.substring(0, 7);
    }

}
