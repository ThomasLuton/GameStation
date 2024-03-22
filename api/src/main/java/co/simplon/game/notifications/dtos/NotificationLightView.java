package co.simplon.game.notifications.dtos;

import java.time.LocalDateTime;

public interface NotificationLightView {

    String getTitle();

    boolean isRead();

    LocalDateTime getSendAt();
}
