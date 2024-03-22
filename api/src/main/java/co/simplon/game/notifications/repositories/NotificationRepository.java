package co.simplon.game.notifications.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.game.notifications.dtos.NotificationDetailView;
import co.simplon.game.notifications.dtos.NotificationLightView;
import co.simplon.game.notifications.entities.Notification;

@Repository
public interface NotificationRepository
	extends JpaRepository<Notification, Long> {

    List<NotificationLightView> findAllProjectedByPlayerId(
	    Long playerId);

    NotificationDetailView findOneById(Long id);

}
