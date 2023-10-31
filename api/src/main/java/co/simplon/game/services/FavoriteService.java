package co.simplon.game.services;

import java.util.List;

import co.simplon.game.dtos.favorite.FavoriteView;

public interface FavoriteService {

    public void add(Long userID, Long game_id);

    public void remove(Long favoriteID);

    public List<FavoriteView> getAllFavoriteForOnePlayer(
	    Long playerID);
}
