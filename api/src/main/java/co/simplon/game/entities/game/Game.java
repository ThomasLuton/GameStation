package co.simplon.game.entities.game;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import co.simplon.game.entities.AbstractEntity;

@Entity
@Table(name = "games")
public class Game extends AbstractEntity {

    @Column(name = "game_name")
    private String gameName;

    @Column(name = "game_detail")
    private String gameDetail;

    @Column(name = "min_player")
    private int minPlayer;

    @Column(name = "max_player")
    private int maxPlayer;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "available")
    private boolean available;

    @Column(name = "victory_point")
    private int victoryPoint;

    @Column(name = "lose_point")
    private int losePoint;

    @Column(name = "draw_point")
    private int drawPoint;

    public Game() {
	super();
	// TODO Auto-generated constructor stub
    }

    public String getGameName() {
	return gameName;
    }

    public void setGameName(String gameName) {
	this.gameName = gameName;
    }

    public String getGameDetail() {
	return gameDetail;
    }

    public void setGameDetail(String gameDetail) {
	this.gameDetail = gameDetail;
    }

    public int getMinPlayer() {
	return minPlayer;
    }

    public void setMinPlayer(int minPlayer) {
	this.minPlayer = minPlayer;
    }

    public int getMaxPlayer() {
	return maxPlayer;
    }

    public void setMaxPlayer(int maxPlayer) {
	this.maxPlayer = maxPlayer;
    }

    public String getThumbnail() {
	return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
	this.thumbnail = thumbnail;
    }

    public boolean isAvailable() {
	return available;
    }

    public void setAvailable(boolean available) {
	this.available = available;
    }

    public int getVictoryPoint() {
	return victoryPoint;
    }

    public void setVictoryPoint(int victoryPoint) {
	this.victoryPoint = victoryPoint;
    }

    public int getLosePoint() {
	return losePoint;
    }

    public void setLosePoint(int losePoint) {
	this.losePoint = losePoint;
    }

    public int getDrawPoint() {
	return drawPoint;
    }

    public void setDrawPoint(int drawPoint) {
	this.drawPoint = drawPoint;
    }

    @Override
    public String toString() {
	return "{gameName=" + gameName + ", gameDetail="
		+ gameDetail + ", minPlayer=" + minPlayer
		+ ", maxPlayer=" + maxPlayer
		+ ", thumbnail=" + thumbnail
		+ ", available=" + available
		+ ", victoryPoint=" + victoryPoint
		+ ", losePoint=" + losePoint
		+ ", drawPoint=" + drawPoint + "}";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = (prime * result) + Objects.hash(gameDetail,
		gameName, thumbnail);
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!super.equals(obj)) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	Game other = (Game) obj;
	return Objects.equals(gameDetail, other.gameDetail)
		&& Objects.equals(gameName, other.gameName)
		&& Objects.equals(thumbnail,
			other.thumbnail);
    }

}
