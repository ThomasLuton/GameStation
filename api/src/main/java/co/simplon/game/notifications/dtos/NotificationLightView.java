package co.simplon.game.notifications.dtos;

import java.time.LocalDateTime;

public interface NotificationLightView {

    Long getId();

    String getTitle();

    boolean isRead();

    LocalDateTime getSendAt();
}
