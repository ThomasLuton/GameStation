package co.simplon.game.dtos.user;

public class UserView {

    private String nickName;

    public UserView() {
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
