package co.simplon.game.sessions.dtos;

import co.simplon.game.sessions.enums.Step;

public record SessionCreated(String sessionCode,
	Step step) {

}
