package co.simplon.game.players.entities;

import java.util.Objects;

import co.simplon.game.entities.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "to_be_friend")
public class Friend extends AbstractEntity {

    @JoinColumn(name = "player_id")
    @ManyToOne
    private Player player;
    @JoinColumn(name = "friend_id")
    @ManyToOne
    private Player friend;

    public Friend() {
	super();
	// TODO Auto-generated constructor stub
    }

    public Player getPlayer() {
	return player;
    }

    public Player getFriend() {
	return friend;
    }

    public void setPlayer(Player player) {
	this.player = player;
    }

    public void setFriend(Player friend) {
	this.friend = friend;
    }

    @Override
    public String toString() {
	return "{player=" + player + ", friend=" + friend
		+ "}";
    }

    @Override
    public int hashCode() {
	return Objects.hash(friend, player);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Friend)) {
	    return false;
	}
	Friend other = (Friend) obj;
	return Objects.equals(friend, other.friend)
		&& Objects.equals(player, other.player);
    }
    
    

}
