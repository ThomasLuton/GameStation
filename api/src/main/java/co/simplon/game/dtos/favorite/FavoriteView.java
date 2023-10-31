package co.simplon.game.dtos.favorite;

public class FavoriteView {

    private Long id;
    private String nickName;
    private String gameName;

    public FavoriteView() {
	super();
	// TODO Auto-generated constructor stub
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getNickName() {
	return nickName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public String getGameName() {
	return gameName;
    }

    public void setGameName(String gameName) {
	this.gameName = gameName;
    }

    @Override
    public String toString() {
	return "{id=" + id + ", nickName=" + nickName
		+ ", gameName=" + gameName + "}";
    }

}
