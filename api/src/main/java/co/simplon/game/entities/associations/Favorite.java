package co.simplon.game.entities.associations;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.simplon.game.entities.AbstractEntity;
import co.simplon.game.entities.game.Game;
import co.simplon.game.entities.user.Player;

@Entity
@Table(name = "favorites")
public class Favorite extends AbstractEntity {

    @JoinColumn(name = "player_id")
    @ManyToOne
    private Player user;
    @JoinColumn(name = "game_id")
    @ManyToOne
    private Game game;

    public Favorite() {
	super();
	// TODO Auto-generated constructor stub
    }

    public Player getUser() {
	return user;
    }

    public void setUser(Player user) {
	this.user = user;
    }

    public Game getGame() {
	return game;
    }

    public void setGame(Game game) {
	this.game = game;
    }

    @Override
    public String toString() {
	return "{user=" + user + ", game=" + game + "}";
    }

}
