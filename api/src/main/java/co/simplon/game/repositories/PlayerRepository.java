package co.simplon.game.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.simplon.game.entities.user.Player;

@Repository
public interface PlayerRepository
	extends JpaRepository<Player, Long> {
    Player findOneProjectedById(Long id);

    Player findOneByEmail(String email);

    Player findOneByNickname(String nickName);

    Player findOneByEmailOrNickname(String email,
	    String nickname);

    List<Player> findAllProjectedByOrderById();

    @Query(value = "Select * from players p where p.connection = true", nativeQuery = true)
    List<Player> findAllConnectedPlayer();

}
