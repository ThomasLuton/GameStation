package co.simplon.game.dtos.game;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import co.simplon.game.validations.GamePoint;
import co.simplon.game.validations.PlayerNumber;

@GamePoint
@PlayerNumber
public class UpdateGame {

    @NotBlank
    @Size(max = 255)
    public String gameName;
    @NotBlank
    @Size(max = 1000)
    public String gameDetail;
    @NotNull
    public int minPlayer;
    @NotNull
    public int maxPlayer;
    @NotNull
    public boolean isAvailable;
    @NotNull
    public int victoryPoint;
    @NotNull
    public int losePoint;
    @NotNull
    public int drawPoint;

    public UpdateGame() {
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

    public boolean isAvailable() {
	return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
	this.isAvailable = isAvailable;
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
		+ ", isAvailable=" + isAvailable
		+ ", victoryPoint=" + victoryPoint
		+ ", losePoint=" + losePoint
		+ ", drawPoint=" + drawPoint + "}";
    }

}
