package co.simplon.game.players.publishers;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.simplon.game.players.dtos.PlayerSimpleView;
import reactor.core.publisher.Sinks;

@Configuration
public class PlayerPublisher {

    @Bean
    public Sinks.Many<List<PlayerSimpleView>> sink() {
	return Sinks.many().multicast()
		.onBackpressureBuffer();
    }
}
