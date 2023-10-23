package co.simplon.game.entities.user;

import java.util.HashSet;
import java.util.Set;

public class ConnectedUsers {

    private static ConnectedUsers instance;
    Set<UserAccount> users;

    private ConnectedUsers() {
	this.users = new HashSet<UserAccount>();
    }

    public static ConnectedUsers getInstance() {
	if (instance == null) {
	    instance = new ConnectedUsers();
	}
	return instance;
    }

    public Set<UserAccount> getUsers() {
	return users;
    }

    @Override
    public String toString() {
	return "{users=" + users + "}";
    }

}
