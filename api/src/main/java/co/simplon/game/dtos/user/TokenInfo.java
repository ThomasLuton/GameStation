package co.simplon.game.dtos.user;

import java.util.List;

import co.simplon.game.dtos.favorite.FavoriteView;

public class TokenInfo {

    private String token;
    private String role;
    private String subject;
    private Long id;
    private List<FavoriteView> favorites;

    public TokenInfo() {
	super();
	// TODO Auto-generated constructor stub
    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public List<FavoriteView> getFavorites() {
	return favorites;
    }

    public void setFavorites(List<FavoriteView> favorites) {
	this.favorites = favorites;
    }

    @Override
    public String toString() {
	return "{token=" + token + ", role=" + role
		+ ", subject=" + subject + ", id=" + id
		+ ", favorites=" + favorites + "}";
    }

}
