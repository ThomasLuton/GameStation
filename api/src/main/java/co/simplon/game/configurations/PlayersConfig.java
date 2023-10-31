package co.simplon.game.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.simplon.game.entities.user.ConnectedPlayers;

@Configuration
public class PlayersConfig {

    @Bean
    public ConnectedPlayers createConnectedUsersSet() {
	return ConnectedPlayers.getInstance();
    }

}
