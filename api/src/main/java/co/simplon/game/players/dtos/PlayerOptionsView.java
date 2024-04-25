package co.simplon.game.players.dtos;

import co.simplon.game.players.entities.GamerTag;

public interface PlayerOptionsView {

    GamerTag getGamerTag();

    Integer getDayBeforeNotification();

    boolean getEmailNotificationEnable();

    String getAvatar();

}
