package co.simplon.game.favorites.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.simplon.game.favorites.dtos.FavoriteView;
import co.simplon.game.favorites.entities.Favorite;
import co.simplon.game.players.entities.Player;

@Repository
public interface FavoriteRepository
	extends JpaRepository<Favorite, Long> {

    @Query(value = "SELECT * FROM to_prefer f WHERE f.player_id = :playerID AND f.game_id = :gameID", nativeQuery = true)
    Favorite getOneByPlayerIDAndGameID(
	    @Param("playerID") Long playerID,
	    @Param("gameID") Long gameID);

    List<FavoriteView> findByPlayer(Player player);

}
