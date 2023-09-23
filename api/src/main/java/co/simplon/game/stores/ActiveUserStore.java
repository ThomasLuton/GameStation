package co.simplon.game.stores;

import java.util.ArrayList;
import java.util.List;

public class ActiveUserStore {

    private List<String> activeUsers;

    public ActiveUserStore() {
	activeUsers = new ArrayList<String>();
    }

    public List<String> getActiveUsers() {
	return activeUsers;
    }

    public void setActiveUsers(List<String> activeUsers) {
	this.activeUsers = activeUsers;
    }

    @Override
    public String toString() {
	return "{activeUsers=" + activeUsers + "}";
    }

}
