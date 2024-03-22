package co.simplon.game.players.entities;

import co.simplon.game.entities.AbstractEntity;
import jakarta.persistence.Column;
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
    @Column(name = "pending")
    private Boolean pending;

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

    public Boolean getPending() {
	return pending;
    }

    public void setPending(Boolean pending) {
	this.pending = pending;
    }

    @Override
    public String toString() {
	return "{player=" + player + ", friend=" + friend
		+ ", pending=" + pending + "}";
    }

}
