package co.simplon.game.notifications.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.game.notifications.dtos.CreateNotification;
import co.simplon.game.notifications.dtos.NotificationDetailView;
import co.simplon.game.notifications.dtos.NotificationLightView;
import co.simplon.game.notifications.entities.Notification;
import co.simplon.game.notifications.repositories.NotificationRepository;

@Service
@Transactional(readOnly = true)
public class NotificationServiceImpl
	implements NotificationService {

    private final NotificationRepository notifications;

    public NotificationServiceImpl(
	    NotificationRepository notifications) {
	this.notifications = notifications;
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
    public void read(Long id) {
	Notification notification = notifications
		.findById(id).get();
	if (!notification.getRead()) {
	    notification.setRead(true);
	}
	notifications.save(notification);
    }

    @Override
    public List<NotificationLightView> getAllForOnePlayer(
	    Long playerId) {
	return notifications
		.findAllProjectedByPlayerId(playerId);
    }

    @Override
    public NotificationDetailView getOneById(Long id) {
	return notifications.findOneById(id);
    }

}
