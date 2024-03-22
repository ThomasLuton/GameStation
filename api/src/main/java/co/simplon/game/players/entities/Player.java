package co.simplon.game.players.entities;

import java.util.Objects;
import java.util.Set;

import co.simplon.game.entities.AbstractEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "players")
public class Player extends AbstractEntity {

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Embedded
    @AttributeOverrides({
	    @AttributeOverride(name = "playerName", column = @Column(name = "player_name")),
	    @AttributeOverride(name = "suffix", column = @Column(name = "suffix")) })
    private GamerTag gamerTag;
    @JoinColumn(name = "role_id")
    @ManyToOne
    private Role role;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "email_notification_enable")
    private boolean emailNotificationEnable;
    @Column(name = "day_before_notification")
    private Integer dayBeforeNotification;
    @Column(name = "is_connected")
    private boolean connection;
    @ManyToMany
    @JoinTable(name = "to_be_friend", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<Player> friends;

    public Player() {
	super();
	// TODO Auto-generated constructor stub
    }

    public String getEmail() {
	return email;
    }

    public String getPassword() {
	return password;
    }

    public GamerTag getGamerTag() {
	return gamerTag;
    }

    public Role getRole() {
	return role;
    }

    public String getAvatar() {
	return avatar;
    }

    public boolean isEmailNotificationEnable() {
	return emailNotificationEnable;
    }

    public Integer getDayBeforeNotification() {
	return dayBeforeNotification;
    }

    public boolean isConnection() {
	return connection;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public void setGamerTag(GamerTag gamerTag) {
	this.gamerTag = gamerTag;
    }

    public void setRole(Role role) {
	this.role = role;
    }

    public void setAvatar(String avatar) {
	this.avatar = avatar;
    }

    public void setEmailNotificationEnable(
	    boolean emailNotificationEnable) {
	this.emailNotificationEnable = emailNotificationEnable;
    }

    public void setDayBeforeNotification(
	    Integer dayBeforeNotification) {
	this.dayBeforeNotification = dayBeforeNotification;
    }

    public void setConnection(boolean connection) {
	this.connection = connection;
    }

    public Set<Player> getFriends() {
	return friends;
    }

    public void setFriends(Set<Player> friends) {
	this.friends = friends;
    }

    @Override
    public String toString() {
	return "{email=" + email + ", gamerTag=" + gamerTag
		+ ", role=" + role + ", avatar=" + avatar
		+ ", emailNotificationEnable="
		+ emailNotificationEnable
		+ ", dayBeforeNotification="
		+ dayBeforeNotification + ", connection="
		+ connection + "}";
    }

    @Override
    public int hashCode() {
	return Objects.hash(avatar, connection,
		dayBeforeNotification, email,
		emailNotificationEnable, friends, gamerTag,
		password, role);
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
	return Objects.equals(avatar, other.avatar)
		&& (connection == other.connection)
		&& Objects.equals(dayBeforeNotification,
			other.dayBeforeNotification)
		&& Objects.equals(email, other.email)
		&& (emailNotificationEnable == other.emailNotificationEnable)
		&& Objects.equals(friends, other.friends)
		&& Objects.equals(gamerTag, other.gamerTag)
		&& Objects.equals(password, other.password)
		&& Objects.equals(role, other.role);
    }

}
