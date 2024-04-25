package co.simplon.game.players.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.game.players.entities.Role;

@Repository
public interface RoleRepository
	extends JpaRepository<Role, Long> {

    Role findOneProjectedByName(String string);

}
