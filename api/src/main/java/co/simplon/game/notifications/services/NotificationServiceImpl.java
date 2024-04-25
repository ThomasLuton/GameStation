package co.simplon.game.notifications.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.game.errors.CodeError;
import co.simplon.game.errors.GameStationError;
import co.simplon.game.notifications.dtos.CreateNotification;
import co.simplon.game.notifications.dtos.NotificationDetailView;
import co.simplon.game.notifications.dtos.NotificationLightView;
import co.simplon.game.notifications.entities.Notification;
import co.simplon.game.notifications.repositories.NotificationRepository;
import co.simplon.game.players.entities.Player;
import co.simplon.game.players.repositories.PlayerRepository;

@Service
@Transactional(readOnly = true)
public class NotificationServiceImpl
	implements NotificationService {

    private final NotificationRepository notifications;
    private final PlayerRepository players;

    public NotificationServiceImpl(
	    NotificationRepository notifications,
	    PlayerRepository player) {
	this.notifications = notifications;
	this.players = player;
    }

    @Override
    @Transactional
    public void create(CreateNotification input) {
	Notification entity = new Notification();
	entity.setTitle(input.title());
	entity.setContent(input.content());
	if (input.redirectLink() != null) {
	    entity.setRedirectLink(input.redirectLink());
	}
	entity.setRead(false);
	entity.setSendAt(LocalDateTime.now());
	entity.setPlayer(input.receiver());
	notifications.save(entity);
    }

    @Override
    @Transactional
    public void markAsUnread(Long id) {
	Notification notification = notifications
		.findById(id).get();
	if (notification.getRead() == false) {
	    throw new GameStationError(
		    CodeError.NotificationAlreadyUnread,
		    "This notification is already in unread state",
		    HttpStatus.BAD_REQUEST);
	}
	notification.setRead(false);
	notifications.save(notification);
    }

    @Override
    public List<NotificationLightView> getAllForOnePlayer(
	    Integer suffix) {
	Player player = players
		.findOneByGamerTagSuffix(suffix);
	return notifications
		.findAllProjectedByPlayerId(player.getId());
    }

    @Override
    @Transactional
    public NotificationDetailView readOneById(Long id) {
	markAsRead(id);
	NotificationDetailView notification = notifications
		.findOneById(id);
	return notification;
    }

    private void markAsRead(Long id) {
	Notification notification = notifications
		.findById(id).get();
	if (notification == null) {
	    throw new GameStationError(
		    CodeError.NotificationNotFound,
		    "There is no notification with this id",
		    HttpStatus.BAD_REQUEST);
	}
	if (notification.getRead()) {
	    return;
	}
	notification.setRead(true);
	notifications.save(notification);
    }

    @Override
    public Integer getNumberOfUnreadNotifications(
	    Integer suffix) {
	Player player = players
		.findOneByGamerTagSuffix(suffix);
	return notifications
		.countUnreadNotifications(player.getId());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
	notifications.deleteById(id);
    }

}
