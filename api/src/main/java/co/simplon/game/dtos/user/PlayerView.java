package co.simplon.game.dtos.user;

import java.util.ArrayList;
import java.util.List;

import co.simplon.game.entities.game.Game;

public class PlayerView {

    private String nickName;
    private List<Game> favorites = new ArrayList<Game>();

    public PlayerView() {
	super();
	// TODO Auto-generated constructor stub
    }

    public String getNickName() {
	return nickName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public List<Game> getFavorites() {
	return favorites;
    }

    public void setFavorites(List<Game> favorites) {
	this.favorites = favorites;
    }

    @Override
    public String toString() {
	return "{nickName=" + nickName + ", favorites="
		+ favorites + "}";
    }

}
