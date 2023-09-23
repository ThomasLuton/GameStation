package co.simplon.game.entities.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import co.simplon.game.entities.AbstractEntity;

@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {

    @Column(name = "name")
    private String name;

    public Role() {
	super();
	// TODO Auto-generated constructor stub
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Override
    public String toString() {
	return "{name=" + name + "}";
    }

}
