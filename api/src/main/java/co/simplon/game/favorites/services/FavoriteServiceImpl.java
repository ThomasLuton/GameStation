package co.simplon.game.favorites.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.game.errors.CodeError;
import co.simplon.game.errors.GameStationError;
import co.simplon.game.favorites.dtos.FavoriteView;
import co.simplon.game.favorites.entities.Favorite;
import co.simplon.game.favorites.repositories.FavoriteRepository;
import co.simplon.game.games.entities.Game;
import co.simplon.game.games.repositories.GameRepository;
import co.simplon.game.players.entities.Player;
import co.simplon.game.players.repositories.PlayerRepository;

@Service
@Transactional(readOnly = true)
public class FavoriteServiceImpl
	implements FavoriteService {

    private final FavoriteRepository favorites;
    private final GameRepository games;
    private final PlayerRepository players;

    public FavoriteServiceImpl(FavoriteRepository favorites,
	    GameRepository games,
	    PlayerRepository players) {
	this.favorites = favorites;
	this.games = games;
	this.players = players;
    }

    @Override
    @Transactional
    public void add(Integer suffix, Long gameID) {
	Player player = players
		.findOneByGamerTagSuffix(suffix);
	Game gameToAdd = games.findById(gameID).get();
	Favorite newFavorite = new Favorite();
	newFavorite.setPlayer(player);
	newFavorite.setGame(gameToAdd);
	favorites.save(newFavorite);
    }

    @Override
    @Transactional
    public void remove(Long favoriteID) {
	Favorite favorite = favorites.findById(favoriteID)
		.orElseThrow(() -> new GameStationError(
			CodeError.NoFavoriteFound,
			"No favorite found with this id",
			HttpStatus.BAD_REQUEST));
	favorites.delete(favorite);
    }

    @Override
    @Transactional
    public Long patch(Integer suffix, Long game_id) {
	Player player = players
		.findOneByGamerTagSuffix(suffix);
	if (player != null) {
	    Long playerID = player.getId();
	    Favorite favorite = favorites
		    .getOneByPlayerIDAndGameID(playerID,
			    game_id);
	    if (favorite == null) {
		this.add(suffix, game_id);
		favorites.flush();
		return favorites.getOneByPlayerIDAndGameID(
			playerID, game_id).getId();
	    }
	    this.remove(favorite.getId());
	}
	return null;
    }

    @Override
    public List<FavoriteView> getAllFavoriteForOnePlayer(
	    Integer suffix) {
	Player player = players
		.findOneByGamerTagSuffix(suffix);
	return favorites.findByPlayer(player);
    }
}
