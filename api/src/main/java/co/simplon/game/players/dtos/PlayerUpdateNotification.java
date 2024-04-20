package co.simplon.game.players.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PlayerUpdateNotification(boolean activate,
	@NotNull @Positive Integer daysBefore) {

}
