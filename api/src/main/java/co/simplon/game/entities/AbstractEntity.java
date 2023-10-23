package co.simplon.game.entities;

import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    public AbstractEntity() {

    }

    public Long getId() {
	return id;
    }

    @SuppressWarnings("unused")
    public void setId(Long id) {
	this.id = id;
    }

    @Override
    public int hashCode() {
	return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	AbstractEntity other = (AbstractEntity) obj;
	return Objects.equals(id, other.id);
    }

}
