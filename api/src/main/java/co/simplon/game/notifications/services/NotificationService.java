package co.simplon.game.notifications.services;

import java.util.List;

import co.simplon.game.notifications.dtos.CreateNotification;
import co.simplon.game.notifications.dtos.NotificationDetailView;
import co.simplon.game.notifications.dtos.NotificationLightView;

public interface NotificationService {

    public void create(CreateNotification input);

    public void read(Long id);

    public List<NotificationLightView> getAllForOnePlayer(
	    Integer suffix);

    public NotificationDetailView getOneById(Long id);

    public Integer getNumberOfUnreadNotifications(
	    Integer suffix);

}
