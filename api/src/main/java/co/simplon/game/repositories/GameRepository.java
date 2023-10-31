package co.simplon.game.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.game.dtos.game.GameDetailView;
import co.simplon.game.dtos.game.GameLightView;
import co.simplon.game.entities.game.Game;

public interface GameRepository
	extends JpaRepository<Game, Long> {

    Collection<GameLightView> findAllProjectedByOrderById();

    GameDetailView findOneProjectById(Long id);

    Game findOneProjectedById(Long id);
}
