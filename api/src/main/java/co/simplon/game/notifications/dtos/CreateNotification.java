package co.simplon.game.notifications.dtos;

import co.simplon.game.players.entities.Player;

public record CreateNotification(String title,
	String content, String redirectLink,
	Player receiver) {

}
