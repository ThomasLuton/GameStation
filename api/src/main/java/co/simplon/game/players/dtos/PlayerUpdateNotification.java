package co.simplon.game.players.dtos;

import co.simplon.game.customValidation.NotificationEnableWithoutDays;
import jakarta.validation.constraints.Positive;

@NotificationEnableWithoutDays
public record PlayerUpdateNotification(boolean activate,
	@Positive Integer daysBefore) {

}
