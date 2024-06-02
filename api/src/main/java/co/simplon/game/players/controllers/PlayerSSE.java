package co.simplon.game.players.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.game.players.dtos.PlayerSimpleView;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@RestController
public class PlayerSSE {

    private final Sinks.Many<List<PlayerSimpleView>> sink;

    public PlayerSSE(
	    Sinks.Many<List<PlayerSimpleView>> sink) {
	this.sink = sink;
    }

    @GetMapping(path = "/connectedPlayers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<List<PlayerSimpleView>> streamActions() {
	return sink.asFlux();
    }

}
