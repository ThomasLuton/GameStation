package co.simplon.game.favorites.entities;

import java.util.Objects;

import co.simplon.game.entities.AbstractEntity;
import co.simplon.game.games.entities.Game;
import co.simplon.game.players.entities.Player;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "to_prefer")
public class Favorite extends AbstractEntity {

    @JoinColumn(name = "player_id")
    @ManyToOne
    private Player player;
    @JoinColumn(name = "game_id")
    @ManyToOne
    private Game game;

    public Favorite() {
	// TODO Auto-generated constructor stub
    }

    public Player getPlayer() {
	return player;
    }

    public Game getGame() {
	return game;
    }

    public void setPlayer(Player player) {
	this.player = player;
    }

    public void setGame(Game game) {
	this.game = game;
    }

    @Override
    public String toString() {
	return "{player=" + player + ", game=" + game + "}";
    }

    @Override
    public int hashCode() {
	return Objects.hash(game, player);
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
		&& Objects.equals(player, other.player);
    }

}
