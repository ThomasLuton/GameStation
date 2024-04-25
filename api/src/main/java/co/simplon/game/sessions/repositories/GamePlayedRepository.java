package co.simplon.game.sessions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.game.sessions.entities.GamePlayed;

@Repository
public interface GamePlayedRepository
	extends JpaRepository<GamePlayed, Long> {

}
