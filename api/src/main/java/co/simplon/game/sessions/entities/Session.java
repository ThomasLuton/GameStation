package co.simplon.game.sessions.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import co.simplon.game.entities.AbstractEntity;
import co.simplon.game.games.entities.Game;
import co.simplon.game.players.entities.Player;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sessions")
public class Session extends AbstractEntity {

    @Column(name = "session_code")
    private String sessionCode;

    @Column(name = "play_at")
    private LocalDateTime playAt;

    @Column(name = "only_friend")
    private boolean onlyFriend;

    @Column(name = "step")
    private Integer step;

    @JoinColumn(name = "game_id")
    @ManyToOne
    private Game game;

    @JoinColumn(name = "player_id")
    @ManyToOne
    private Player creator;

    public Session() {
	// TODO Auto-generated constructor stub
    }

    public String getSessionCode() {
	return sessionCode;
    }

    public LocalDateTime getPlayAt() {
	return playAt;
    }

    public boolean isOnlyFriend() {
	return onlyFriend;
    }

    public Integer getStep() {
	return step;
    }

    public Game getGame() {
	return game;
    }

    public Player getCreator() {
	return creator;
    }

    public void setSessionCode(String sessionCode) {
	this.sessionCode = sessionCode;
    }

    public void setPlayAt(LocalDateTime playAt) {
	this.playAt = playAt;
    }

    public void setOnlyFriend(boolean onlyFriend) {
	this.onlyFriend = onlyFriend;
    }

    public void setStep(Integer step) {
	this.step = step;
    }

    public void setGame(Game game) {
	this.game = game;
    }

    public void setCreator(Player creator) {
	this.creator = creator;
    }

    @Override
    public String toString() {
	return "{sessionCode=" + sessionCode + ", playAt="
		+ playAt + ", onlyFriend=" + onlyFriend
		+ ", step=" + step + ", game=" + game
		+ ", creator=" + creator + "}";
    }

    @Override
    public int hashCode() {
	return Objects.hash(creator, game, onlyFriend,
		playAt, sessionCode, step);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Session)) {
	    return false;
	}
	Session other = (Session) obj;
	return Objects.equals(creator, other.creator)
		&& Objects.equals(game, other.game)
		&& (onlyFriend == other.onlyFriend)
		&& Objects.equals(playAt, other.playAt)
		&& Objects.equals(sessionCode,
			other.sessionCode)
		&& Objects.equals(step, other.step);
    }

}
