package co.simplon.game.players.entities;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class GamerTag {

    private String playerName;
    private Integer suffix;

    public GamerTag() {
	super();
	// TODO Auto-generated constructor stub
    }

    public String getPlayerName() {
	return playerName;
    }

    public Integer getSuffix() {
	return suffix;
    }

    public void setPlayerName(String playerName) {
	this.playerName = playerName;
    }

    public void setSuffix(Integer suffix) {
	this.suffix = suffix;
    }

    @Override
    public String toString() {
	return String.format("%s#%d", playerName, suffix);
    }

    @Override
    public int hashCode() {
	return Objects.hash(playerName, suffix);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	return obj instanceof GamerTag other // pattern matching
		&& Objects.equals(playerName,
			other.playerName)
		&& Objects.equals(suffix, other.suffix);
    }
}
