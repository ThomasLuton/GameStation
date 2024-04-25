package co.simplon.game.games.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.game.games.dtos.GameDetailView;
import co.simplon.game.games.dtos.GameLightView;
import co.simplon.game.games.entities.Game;

@Repository
public interface GameRepository
	extends JpaRepository<Game, Long> {

    List<GameLightView> findAllProjectedByOrderById();

    GameDetailView findOneById(Long id);

}
