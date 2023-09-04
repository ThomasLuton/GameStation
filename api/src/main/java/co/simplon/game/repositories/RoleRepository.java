package co.simplon.game.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.game.entities.authentication.Role;

@Repository
public interface RoleRepository
	extends JpaRepository<Role, Long> {
    Role findOneProjectedById(Long id);
}
