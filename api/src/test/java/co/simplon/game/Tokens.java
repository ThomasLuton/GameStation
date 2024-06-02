package co.simplon.game;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class Tokens {

    @Value("${gameStation.tests.fake-token}")
    private String fake;

    @Value("${gameStation.tests.bad-secret-token}")
    private String badSecret;

    @Value("${gameStation.tests.bad-issuer-token}")
    private String badIssuer;

    @Value("${gameStation.tests.expired-token}")
    private String expired;

    @Value("${gameStation.tests.valid-player-token}")
    private String player;

    @Value("${gameStation.tests.valid-admin-token}")
    private String admin;

    String get(final String name) {
	switch (name) {
	case "fake":
	    return fake;
	case "badSecret":
	    return badSecret;
	case "badIssuer":
	    return badIssuer;
	case "expired":
	    return expired;
	case "player":
	    return player;
	case "admin":
	    return admin;
	default:
	    throw new IllegalArgumentException(
		    "Unexpected value: " + name);
	}
    }

}