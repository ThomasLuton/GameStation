package co.simplon.game.players.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.game.errors.CodeError;
import co.simplon.game.errors.GameStationError;
import co.simplon.game.notifications.dtos.CreateNotification;
import co.simplon.game.notifications.services.NotificationService;
import co.simplon.game.players.dtos.GamerTagDto;
import co.simplon.game.players.entities.Friend;
import co.simplon.game.players.entities.Player;
import co.simplon.game.players.repositories.FriendRepository;
import co.simplon.game.players.repositories.PlayerRepository;

@Service
@Transactional(readOnly = true)
public class FriendServiceImpl implements FriendService {

    /*
     * Refacto possible le code de vérification se répète beaucoup à étudier
     */

    private final PlayerRepository players;
    private final FriendRepository friends;
    private final NotificationService notificationService;

    public FriendServiceImpl(PlayerRepository players,
	    FriendRepository friends,
	    NotificationService notificationService) {
	this.players = players;
	this.friends = friends;
	this.notificationService = notificationService;
    }

    @Override
    @Transactional
    public void request(GamerTagDto newFriend,
	    Integer playerSuffix) {
	Player friend = getPlayer(newFriend);
	if (friend == null) {
	    throw new GameStationError(
		    CodeError.UnknownPlayer,
		    "This player doesn't exist",
		    HttpStatus.BAD_REQUEST);
	}
	Player player = players
		.findOneByGamerTagSuffix(playerSuffix);

	if (player.equals(friend)) {
	    throw new GameStationError(CodeError.SamePlayer,
		    "You can be friend with yourself (it's very sad, dude)",
		    HttpStatus.BAD_REQUEST);
	}

	Friend friendShip = friends
		.findOneFriendShipRelation(player.getId(),
			friend.getId());

	if (friendShip != null) {
	    throw new GameStationError(
		    CodeError.AlreadyFriend,
		    "You are already friend with this player or your demand is pending",
		    HttpStatus.BAD_REQUEST);
	}

	CreateNotification request = new CreateNotification(
		"Demande d'ami",
		"Je suis " + player.getGamerTag()
			+ ", veux tu être mon ami?",
		null, friend);
	notificationService.create(request);
	Friend entity = new Friend();
	entity.setPlayer(player);
	entity.setFriend(friend);
	entity.setPending(true);
	this.friends.save(entity);
    }

    @Override
    @Transactional
    public void accept(GamerTagDto newFriend,
	    Integer userSuffix) {
	// attention verifier si notification existe avant
	Player friend = getPlayer(newFriend);
	Player player = players
		.findOneByGamerTagSuffix(userSuffix);

	Friend friendship = friends
		.findOneFriendShipRelation(player.getId(),
			friend.getId());

	if (friendship == null) {
	    throw new GameStationError(
		    CodeError.NotFriendWith,
		    "There is no relation between you and this player",
		    HttpStatus.BAD_REQUEST);
	}
	if (!friendship.getPending()) {
	    throw new GameStationError(
		    CodeError.AlreadyFriend,
		    "You are already friend with this player",
		    HttpStatus.BAD_REQUEST);
	}
	if (friendship.getPlayer().equals(player)) {
	    throw new GameStationError(
		    CodeError.NotYourTurn,
		    "It's not to you to response",
		    HttpStatus.BAD_REQUEST);
	}
	CreateNotification response = new CreateNotification(
		"Demande d'ami",
		"Vous êtes maintenant ami avec "
			+ player.getGamerTag(),
		null, friend);
	notificationService.create(response);

	friendship.setPending(false);
	friends.save(friendship);
    }

    @Override
    @Transactional
    public void refuse(GamerTagDto newFriend,
	    Integer userSuffix) {
	// attention verifier si notification existe avant
	Player friend = getPlayer(newFriend);
	Player player = players
		.findOneByGamerTagSuffix(userSuffix);

	Friend friendship = friends
		.findOneFriendShipRelation(player.getId(),
			friend.getId());
	if (friendship == null) {
	    throw new GameStationError(
		    CodeError.NotFriendWith,
		    "There is no relation between you and this player",
		    HttpStatus.BAD_REQUEST);
	}
	if (!friendship.getPending()) {
	    throw new GameStationError(
		    CodeError.AlreadyFriend,
		    "You are already friend with this player",
		    HttpStatus.BAD_REQUEST);
	}
	if (friendship.getPlayer().equals(player)) {
	    throw new GameStationError(
		    CodeError.NotYourTurn,
		    "It's not to you to response",
		    HttpStatus.BAD_REQUEST);
	}
	CreateNotification response = new CreateNotification(
		"Demande d'ami",
		player.getGamerTag()
			+ " a refusé votre demande d'ami",
		null, friend);
	notificationService.create(response);
	friends.delete(friendship);

    }

    @Override
    @Transactional
    public void delete(GamerTagDto oldFriend,
	    Integer userSuffix) {
	Player friend = getPlayer(oldFriend);
	Player player = players
		.findOneByGamerTagSuffix(userSuffix);
	Friend oldRelation = friends
		.findOneFriendShipRelation(player.getId(),
			friend.getId());
	if (oldRelation == null) {
	    throw new GameStationError(
		    CodeError.NotFriendWith,
		    "You are not friend with "
			    + friend.getGamerTag(),
		    HttpStatus.BAD_REQUEST);
	}
	CreateNotification notification = new CreateNotification(
		"Demande d'ami",
		player.getGamerTag()
			+ " n'est plus votre ami",
		null, friend);
	notificationService.create(notification);
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
