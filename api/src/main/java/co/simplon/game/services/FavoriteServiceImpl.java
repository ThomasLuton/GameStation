package co.simplon.game.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.game.dtos.favorite.FavoriteView;
import co.simplon.game.entities.associations.Favorite;
import co.simplon.game.entities.game.Game;
import co.simplon.game.entities.user.Player;
import co.simplon.game.repositories.FavoriteRepository;
import co.simplon.game.repositories.GameRepository;
import co.simplon.game.repositories.PlayerRepository;

@Service
@Transactional(readOnly = true)
public class FavoriteServiceImpl
	implements FavoriteService {

    private final FavoriteRepository favorites;
    private final GameRepository games;
    private final PlayerRepository users;

    public FavoriteServiceImpl(FavoriteRepository favorites,
	    GameRepository games, PlayerRepository users) {
	this.favorites = favorites;
	this.games = games;
	this.users = users;
    }

    @Override
    @Transactional
    public void add(Long userID, Long gameID) {
	Player user = users.findOneProjectedById(userID);
	Game gameToAdd = games.findOneProjectedById(gameID);
	Favorite newFavorite = new Favorite();
	newFavorite.setUser(user);
	newFavorite.setGame(gameToAdd);
	favorites.save(newFavorite);
    }

    @Override
    @Transactional
    public void remove(Long favoriteID) {
	favorites.deleteById(favoriteID);
    }

    @Override
    public List<FavoriteView> getAllFavoriteForOnePlayer(
	    Long playerID) {
	List<Favorite> playerFavorites = favorites
		.getAllForOnePlayer(playerID);
	List<FavoriteView> output = new ArrayList<FavoriteView>();
	for (Favorite favorite : playerFavorites) {
	    FavoriteView view = new FavoriteView();
	    view.setId(favorite.getId());
	    view.setNickName(
		    favorite.getUser().getNickname());
	    view.setGameName(
		    favorite.getGame().getGameName());
	    output.add(view);
	}
	return output;
    }

}
