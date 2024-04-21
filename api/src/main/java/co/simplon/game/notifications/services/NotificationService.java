package co.simplon.game.notifications.services;

import java.util.List;

import co.simplon.game.notifications.dtos.CreateNotification;
import co.simplon.game.notifications.dtos.NotificationDetailView;
import co.simplon.game.notifications.dtos.NotificationLightView;

public interface NotificationService {

    public void create(CreateNotification input);

    public void markAsUnread(Long id);

    public List<NotificationLightView> getAllForOnePlayer(
	    Integer suffix);

    public NotificationDetailView readOneById(Long id);

    public Integer getNumberOfUnreadNotifications(
	    Integer suffix);

    public void deleteById(Long id);

}
