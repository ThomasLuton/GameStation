package co.simplon.game.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.simplon.game.entities.associations.Favorite;

@Repository
public interface FavoriteRepository
	extends JpaRepository<Favorite, Long> {

    @Query(value = "SELECT * FROM favorites f WHERE f.player_id = :playerID", nativeQuery = true)
    List<Favorite> getAllForOnePlayer(
	    @Param("playerID") Long playerID);

}
