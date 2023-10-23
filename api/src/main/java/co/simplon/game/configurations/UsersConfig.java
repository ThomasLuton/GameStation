package co.simplon.game.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.simplon.game.entities.user.ConnectedUsers;

@Configuration
public class UsersConfig {

    @Bean
    public ConnectedUsers createConnectedUsersSet() {
	return ConnectedUsers.getInstance();
    }

}
