package co.simplon.game.sessions.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.simplon.game.sessions.entities.Session;

@Repository
public interface SessionRepository
	extends JpaRepository<Session, Long> {

    @Query(value = "select count(tp) from sessions s join to_play tp on tp.player_id = :playerId where s.step in (1,2);", nativeQuery = true)
    Integer countSessionNotFinishForOnePlayer(
	    @Param("playerId") Long playerId);

    Optional<Session> findBySessionCode(String sessionCode);

    @Query(value = "select s.* from sessions s join to_play tp on tp.player_id = :playerId where s.step = 1;", nativeQuery = true)
    Session getSessionInDraftForOnePlayer(
	    @Param("playerId") Long playerId);

}
