package co.simplon.game.sessions.entities;

import java.util.Objects;

import co.simplon.game.entities.AbstractEntity;
import co.simplon.game.players.entities.Player;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "to_play")
public class GamePlayed extends AbstractEntity {

    @JoinColumn(name = "player_id")
    @ManyToOne
    private Player player;
    @JoinColumn(name = "session_id")
    @ManyToOne
    private Session session;
    @JoinColumn(name = "result_id")
    @ManyToOne
    private Result result;

    public GamePlayed() {
	// TODO Auto-generated constructor stub
    }

    public Player getPlayer() {
	return player;
    }

    public Session getSession() {
	return session;
    }

    public Result getResult() {
	return result;
    }

    public void setPlayer(Player player) {
	this.player = player;
    }

    public void setSession(Session session) {
	this.session = session;
    }

    public void setResult(Result result) {
	this.result = result;
    }

    @Override
    public String toString() {
	return "{player=" + player + ", session=" + session
		+ ", result=" + result + "}";
    }

    @Override
    public int hashCode() {
	return Objects.hash(player, result, session);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof GamePlayed)) {
	    return false;
	}
	GamePlayed other = (GamePlayed) obj;
	return Objects.equals(player, other.player)
		&& Objects.equals(result, other.result)
		&& Objects.equals(session, other.session);
    }

}
