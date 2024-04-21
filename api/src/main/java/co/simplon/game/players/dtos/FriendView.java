package co.simplon.game.players.dtos;

import co.simplon.game.players.entities.GamerTag;

public class FriendView {

    private GamerTag gamerTag;
    private String avatar;
    private boolean pending;

    public FriendView() {
	// TODO Auto-generated constructor stub
    }

    public GamerTag getGamerTag() {
	return gamerTag;
    }

    public String getAvatar() {
	return avatar;
    }

    public boolean isPending() {
	return pending;
    }

    public void setGamerTag(GamerTag gamerTag) {
	this.gamerTag = gamerTag;
    }

    public void setAvatar(String avatar) {
	this.avatar = avatar;
    }

    public void setPending(boolean pending) {
	this.pending = pending;
    }

    @Override
    public String toString() {
	return "{gamerTag=" + gamerTag + ", avatar="
		+ avatar + ", pending=" + pending + "}";
    }

}
