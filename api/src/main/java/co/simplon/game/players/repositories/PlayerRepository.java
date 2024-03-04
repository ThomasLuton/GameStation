package co.simplon.game.players.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.simplon.game.players.dtos.PlayerSimpleView;
import co.simplon.game.players.entities.Player;

@Repository
public interface PlayerRepository
	extends JpaRepository<Player, Long> {

    Player findOneByEmail(String email);

    @Query(value = "select * from players p where p.suffix = :suffix", nativeQuery = true)
    Player findOneByGamerTagSuffix(
	    @Param("suffix") Integer suffix);

    List<PlayerSimpleView> findAllProjectedByConnection(
	    boolean connection);

    List<PlayerSimpleView> findAllProjectedByOrderById();

}
