package co.simplon.game.notifications.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(value = "select count(*) from notifications n where n.player_id = :playerId and n.is_read is false", nativeQuery = true)
    Integer countUnreadNotifications(
	    @Param("playerId") Long playerId);

}
