package co.simplon.game.players.services;

import co.simplon.game.players.dtos.GamerTagDto;

public interface FriendService {

    public void request(GamerTagDto newFriend,
	    Integer userSuffix);

    public void accept(GamerTagDto newFriend,
	    Integer userSuffix);

    public void refuse(GamerTagDto newFriend);

    public void delete(GamerTagDto oldFriend,
	    Integer userSuffix);
}
