package co.simplon.game.dtos.user;

import java.util.List;

public class ConnectedUsersList {

    private List<String> connectedUsers;

    public ConnectedUsersList() {
	super();
	// TODO Auto-generated constructor stub
    }

    public List<String> getConnectedUsers() {
	return connectedUsers;
    }

    public void setConnectedUsers(
	    List<String> connectedUsers) {
	this.connectedUsers = connectedUsers;
    }

    @Override
    public String toString() {
	return "{connectedUsers=" + connectedUsers + "}";
    }

}
