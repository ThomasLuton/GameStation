package co.simplon.game.entities.user;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.simplon.game.entities.AbstractEntity;

@Entity
@Table(name = "players")
public class Player extends AbstractEntity {

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "nickname")
    private String nickname;
    @JoinColumn(name = "role_id")
    @ManyToOne
    private Role role;
    @Column(name = "coins")
    private int coins;
    @Column(name = "connection")
    private boolean connection;

    public Player() {
	super();
	// TODO Auto-generated constructor stub
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Role getRole() {
	return role;
    }

    public void setRole(Role role) {
	this.role = role;
    }

    public String getNickname() {
	return nickname;
    }

    public void setNickname(String nickname) {
	this.nickname = nickname;
    }

    public int getCoins() {
	return coins;
    }

    public void setCoins(int coins) {
	this.coins = coins;
    }

    public boolean isConnection() {
	return connection;
    }

    public void setConnection(boolean connection) {
	this.connection = connection;
    }

    @Override
    public String toString() {
	return "{email=" + email + ", nickname=" + nickname
		+ ", role=" + role + ", coins=" + coins
		+ ", connection=" + connection + "}";
    }

    @Override
    public int hashCode() {
	return Objects.hash(email);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Player)) {
	    return false;
	}
	Player other = (Player) obj;
	return Objects.equals(email, other.email);
    }

}
