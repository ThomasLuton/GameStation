package co.simplon.game.dtos.user;

public class PlayerConnection {

    private String nickname;

    public PlayerConnection() {
	super();
	// TODO Auto-generated constructor stub
    }

    public String getNickname() {
	return nickname;
    }

    public void setNickname(String nickname) {
	this.nickname = nickname;
    }

    @Override
    public String toString() {
	return "{nickname=" + nickname + "}";
    }

}
