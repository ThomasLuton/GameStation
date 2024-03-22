package co.simplon.game.players.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.game.errors.CodeError;
import co.simplon.game.errors.GameStationError;
import co.simplon.game.players.dtos.GamerTagDto;
import co.simplon.game.players.entities.Friend;
import co.simplon.game.players.entities.Player;
import co.simplon.game.players.repositories.FriendRepository;
import co.simplon.game.players.repositories.PlayerRepository;

@Service
@Transactional(readOnly = true)
public class FriendServiceImpl implements FriendService {

    private final PlayerRepository players;
    private final FriendRepository friends;

    public FriendServiceImpl(PlayerRepository players,
	    FriendRepository friends) {
	this.players = players;
	this.friends = friends;
    }

    @Override
    public void request(GamerTagDto newFriend) {
	Player friend = getPlayer(newFriend);
	System.out.println("Notification sent to "
		+ friend.getGamerTag());

    }

    @Override
    @Transactional
    public void accept(GamerTagDto newFriend,
	    Integer userSuffix) {
	// attention verifier si notification existe avant
	Player friend = getPlayer(newFriend);
	Player user = players
		.findOneByGamerTagSuffix(userSuffix);
	Friend relation = new Friend();
	relation.setPlayer(user);
	relation.setFriend(friend);
	friends.save(relation);
    }

    @Override
    public void refuse(GamerTagDto newFriend) {
	// attention verifier si notification existe avant
	Player friend = getPlayer(newFriend);
	System.out.println("Je ne suis pas ton pote "
		+ friend.getGamerTag());

    }

    @Override
    @Transactional
    public void delete(GamerTagDto oldFriend,
	    Integer userSuffix) {
	Player friend = getPlayer(oldFriend);
	Player user = players
		.findOneByGamerTagSuffix(userSuffix);
	Friend oldRelation = friends
		.findOneProjectedByPlayerIdAndFriendId(
			user.getId(), friend.getId());
	if (oldRelation == null) {
	    throw new GameStationError(
		    CodeError.NotFriendWith,
		    "You are not friend with "
			    + friend.getGamerTag(),
		    HttpStatus.BAD_REQUEST);
	}
	friends.delete(oldRelation);
    }

    private Player getPlayer(GamerTagDto newFriend) {
	Player friend = players.findOneByGamerTagSuffix(
		newFriend.suffix());
	if (friend == null) {
	    throw new GameStationError(
		    CodeError.UnknownPlayer,
		    String.format("%s doesn't exist",
			    newFriend.name()),
		    HttpStatus.BAD_REQUEST);
	}
	return friend;
    }
}
