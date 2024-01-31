package co.simplon.game.entities.associations;

import java.util.Objects;

import co.simplon.game.entities.AbstractEntity;
import co.simplon.game.entities.game.Game;
import co.simplon.game.entities.user.Player;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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

    @Override
    public int hashCode() {
	return Objects.hash(game, user);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Favorite)) {
	    return false;
	}
	Favorite other = (Favorite) obj;
	return Objects.equals(game, other.game)
		&& Objects.equals(user, other.user);
    }

}
