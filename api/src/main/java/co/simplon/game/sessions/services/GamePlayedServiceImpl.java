package co.simplon.game.sessions.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.game.players.entities.Player;
import co.simplon.game.sessions.entities.GamePlayed;
import co.simplon.game.sessions.entities.Session;
import co.simplon.game.sessions.repositories.GamePlayedRepository;

@Service
@Transactional(readOnly = true)
public class GamePlayedServiceImpl
	implements GamePlayedService {

    private GamePlayedRepository gamesPlayed;

    public GamePlayedServiceImpl(
	    GamePlayedRepository gamesPlayed) {
	this.gamesPlayed = gamesPlayed;
    }

    @Override
    @Transactional
    public void create(Player player, Session session) {
	GamePlayed gamePlayed = new GamePlayed();
	gamePlayed.setPlayer(player);
	gamePlayed.setSession(session);
	gamesPlayed.save(gamePlayed);
    }
}
