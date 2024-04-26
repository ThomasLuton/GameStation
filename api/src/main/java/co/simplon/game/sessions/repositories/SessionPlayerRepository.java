package co.simplon.game.sessions.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.game.sessions.entities.SessionPlayer;
import co.simplon.game.sessions.entities.Session;

@Repository
public interface SessionPlayerRepository
	extends JpaRepository<SessionPlayer, Long> {

    List<SessionPlayer> findAllBySession(Session session);

}
