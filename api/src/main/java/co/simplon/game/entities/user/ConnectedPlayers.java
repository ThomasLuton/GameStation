package co.simplon.game.entities.user;

import java.util.HashSet;
import java.util.Set;

public class ConnectedPlayers {

    private static ConnectedPlayers instance;
    Set<Player> users;

    private ConnectedPlayers() {
	this.users = new HashSet<Player>();
    }

    public static ConnectedPlayers getInstance() {
	if (instance == null) {
	    instance = new ConnectedPlayers();
	}
	return instance;
    }

    public Set<Player> getUsers() {
	return users;
    }

    @Override
    public String toString() {
	return "{users=" + users + "}";
    }

}
