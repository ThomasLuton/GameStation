package co.simplon.game.players.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.game.players.entities.Friend;

@Repository
public interface FriendRepository
	extends JpaRepository<Friend, Long> {

    Friend findOneProjectedByPlayerIdAndFriendId(
	    Long playerId, Long friendId);

}
