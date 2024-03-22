package co.simplon.game.notifications.dtos;

import java.time.LocalDateTime;

public interface NotificationDetailView {

    String getTitle();

    String getContent();

    LocalDateTime getSendAt();

    boolean isRead();

    String getRedirectLink();

}
