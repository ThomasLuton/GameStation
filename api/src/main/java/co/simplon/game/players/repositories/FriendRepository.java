package co.simplon.game.players.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.simplon.game.players.entities.Friend;
import co.simplon.game.players.entities.Player;

@Repository
public interface FriendRepository
	extends JpaRepository<Friend, Long> {

    @Query(value = "SELECT * FROM to_be_friend"
	    + " WHERE (player_id = :playerId AND friend_id = :friendId)"
	    + " OR (player_id = :friendId AND friend_id = :playerId);", nativeQuery = true)
    Friend findOneFriendShipRelation(
	    @Param("playerId") Long playerId,
	    @Param("friendId") Long friendId);

    List<Friend> findByPlayerOrFriend(Player player,
	    Player friend);

}
