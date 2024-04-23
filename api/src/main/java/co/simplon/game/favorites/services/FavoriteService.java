package co.simplon.game.favorites.services;

import java.util.List;

import co.simplon.game.favorites.dtos.FavoriteView;

public interface FavoriteService {

    public void add(Integer suffix, Long game_id);

    public void remove(Long favoriteID);

    public Long patch(Integer suffix, Long game_id);

    public List<FavoriteView> getAllFavoriteForOnePlayer(
	    Integer suffix);
}