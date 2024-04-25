package co.simplon.game.sessions.entities;

import java.util.Objects;

import co.simplon.game.entities.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "results")
public class Result extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "ranking")
    private Integer ranking;

    public Result() {
	// TODO Auto-generated constructor stub
    }

    public String getName() {
	return name;
    }

    public Integer getRanking() {
	return ranking;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setRanking(Integer ranking) {
	this.ranking = ranking;
    }

    @Override
    public String toString() {
	return "{name=" + name + ", ranking=" + ranking
		+ "}";
    }

    @Override
    public int hashCode() {
	return Objects.hash(name, ranking);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Result)) {
	    return false;
	}
	Result other = (Result) obj;
	return Objects.equals(name, other.name)
		&& Objects.equals(ranking, other.ranking);
    }

}
