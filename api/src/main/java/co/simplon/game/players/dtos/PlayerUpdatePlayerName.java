package co.simplon.game.players.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PlayerUpdatePlayerName(
	@Size(max = 20) @NotBlank String playerName) {

}
