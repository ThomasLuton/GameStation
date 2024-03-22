package co.simplon.game.games.entities;

import co.simplon.game.entities.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "games")
public class Game extends AbstractEntity {

    @Column(name = "game_name")
    private String gameName;
    @Column(name = "game_detail")
    private String gameDetail;
    @Column(name = "min_player")
    private Integer minPlayer;
    @Column(name = "max_player")
    private Integer maxPlayer;
    @Column(name = "thumbnail")
    private String thumbnail;
    @Column(name = "ai_available")
    private Boolean aiAvailable;

    public Game() {
	// TODO Auto-generated constructor stub
    }

    public String getGameName() {
	return gameName;
    }

    public String getGameDetail() {
	return gameDetail;
    }

    public Integer getMinPlayer() {
	return minPlayer;
    }

    public Integer getMaxPlayer() {
	return maxPlayer;
    }

    public String getThumbnail() {
	return thumbnail;
    }

    public Boolean getAiAvailable() {
	return aiAvailable;
    }

    public void setGameName(String gameName) {
	this.gameName = gameName;
    }

    public void setGameDetail(String gameDetail) {
	this.gameDetail = gameDetail;
    }

    public void setMinPlayer(Integer minPlayer) {
	this.minPlayer = minPlayer;
    }

    public void setMaxPlayer(Integer maxPlayer) {
	this.maxPlayer = maxPlayer;
    }

    public void setThumbnail(String thumbnail) {
	this.thumbnail = thumbnail;
    }

    public void setAiAvailable(Boolean aiAvailable) {
	this.aiAvailable = aiAvailable;
    }

    @Override
    public String toString() {
	return "{gameName=" + gameName + ", gameDetail="
		+ gameDetail + ", minPlayer=" + minPlayer
		+ ", maxPlayer=" + maxPlayer
		+ ", thumbnail=" + thumbnail
		+ ", aiAvailable=" + aiAvailable + "}";
    }

}
