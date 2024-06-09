package ro.upt.etc.licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.upt.etc.licenta.repository.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsernameContainingIgnoreCase(String username);
}
