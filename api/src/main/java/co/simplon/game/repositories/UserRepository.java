package co.simplon.game.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.game.entities.user.UserAccount;

@Repository
public interface UserRepository
	extends JpaRepository<UserAccount, Long> {
    UserAccount findOneByEmail(String email);

    UserAccount findOneByEmailOrNickname(String email,
	    String nickname);
}
