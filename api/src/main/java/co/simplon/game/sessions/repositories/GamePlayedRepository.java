package co.simplon.game.sessions.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.game.sessions.entities.GamePlayed;
import co.simplon.game.sessions.entities.Session;

@Repository
public interface GamePlayedRepository
	extends JpaRepository<GamePlayed, Long> {

    List<GamePlayed> findAllBySession(Session session);

}
