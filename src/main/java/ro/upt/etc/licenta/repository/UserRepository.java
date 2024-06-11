package ro.upt.etc.licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.upt.etc.licenta.repository.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsernameContainingIgnoreCase(String username);

    Optional<User> findByUsername(String username);
}
