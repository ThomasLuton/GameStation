package co.simplon.game.players.entities;

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
	return "{playerName=" + playerName + ", suffix="
		+ suffix + "}";
    }

}
