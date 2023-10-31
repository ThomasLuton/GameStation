package co.simplon.game.dtos.user;

import java.util.List;

import co.simplon.game.dtos.favorite.FavoriteView;

public class PlayerDetailView {

    private String nickName;
    private List<FavoriteView> favorites;

    public PlayerDetailView() {
	super();
	// TODO Auto-generated constructor stub
    }

    public String getNickName() {
	return nickName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public List<FavoriteView> getFavorites() {
	return favorites;
    }

    public void setFavorites(List<FavoriteView> favorites) {
	this.favorites = favorites;
    }

    @Override
    public String toString() {
	return "{nickName=" + nickName + ", favorites="
		+ favorites + "}";
    }

}
