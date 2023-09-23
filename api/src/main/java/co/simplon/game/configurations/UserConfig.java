package co.simplon.game.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.simplon.game.stores.ActiveUserStore;

@Configuration
public class UserConfig {

    @Bean
    public ActiveUserStore activeUserStore() {
	return new ActiveUserStore();
    }

}
