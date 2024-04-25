package co.simplon.game.players.services;

import java.util.List;

import co.simplon.game.players.dtos.FriendView;
import co.simplon.game.players.dtos.GamerTagDto;

public interface FriendService {

    public void request(GamerTagDto newFriend,
	    Integer userSuffix);

    public void accept(GamerTagDto newFriend,
	    Integer userSuffix);

    public void refuse(GamerTagDto newFriend,
	    Integer userSuffix);

    public void delete(GamerTagDto oldFriend,
	    Integer userSuffix);

    public List<FriendView> getFriends(Integer suffix);
}
