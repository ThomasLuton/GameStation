package co.simplon.game.notifications.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import co.simplon.game.entities.AbstractEntity;
import co.simplon.game.players.entities.Player;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notifications")
public class Notification extends AbstractEntity {

    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "send_at")
    private LocalDateTime sendAt;
    @Column(name = "is_read")
    private Boolean read;
    @Column(name = "redirect_link")
    private String redirectLink;
    @JoinColumn(name = "player_id")
    @ManyToOne
    private Player player;

    public Notification() {
	// TODO Auto-generated constructor stub
    }

    public String getTitle() {
	return title;
    }

    public String getContent() {
	return content;
    }

    public LocalDateTime getSendAt() {
	return sendAt;
    }

    public Boolean getRead() {
	return read;
    }

    public String getRedirectLink() {
	return redirectLink;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public void setSendAt(LocalDateTime sendAt) {
	this.sendAt = sendAt;
    }

    public void setRead(Boolean read) {
	this.read = read;
    }

    public void setRedirectLink(String redirectLink) {
	this.redirectLink = redirectLink;
    }

    public Player getPlayer() {
	return player;
    }

    public void setPlayer(Player player) {
	this.player = player;
    }

    @Override
    public String toString() {
	return "{title=" + title + ", content=" + content
		+ ", sendAt=" + sendAt + ", read=" + read
		+ ", redirectLink=" + redirectLink
		+ ", player=" + player + "}";
    }

    @Override
    public int hashCode() {
	return Objects.hash(content, player, sendAt, title);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Notification)) {
	    return false;
	}
	Notification other = (Notification) obj;
	return Objects.equals(content, other.content)
		&& Objects.equals(player, other.player)
		&& Objects.equals(sendAt, other.sendAt)
		&& Objects.equals(title, other.title);
    }

}
