package co.simplon.game;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import co.simplon.game.players.controllers.PlayerController;

@TestConfiguration
class ControllerMocks {

    @Bean
    PlayerController playerController() {
	return Mockito.mock(PlayerController.class);
    }

}
