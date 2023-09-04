package co.simplon.game.entities;

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

}
