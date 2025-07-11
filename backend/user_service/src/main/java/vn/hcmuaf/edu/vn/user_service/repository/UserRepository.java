package vn.hcmuaf.edu.vn.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hcmuaf.edu.vn.user_service.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
