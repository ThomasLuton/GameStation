package co.simplon.game.dtos.user;

public class PlayerView {

    private String nickName;

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

    @Override
    public String toString() {
	return "{nickName=" + nickName + "}";
    }

}
