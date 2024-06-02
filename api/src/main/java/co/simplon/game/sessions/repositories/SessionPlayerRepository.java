package co.simplon.game.sessions.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.game.players.entities.Player;
import co.simplon.game.sessions.entities.Session;
import co.simplon.game.sessions.entities.SessionPlayer;

@Repository
public interface SessionPlayerRepository
	extends JpaRepository<SessionPlayer, Long> {

    List<SessionPlayer> findAllBySession(Session session);

    Optional<SessionPlayer> findOneByPlayer(Player player);

}
