package server.ua.semesterWorkServer.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import server.ua.semesterWorkServer.entities.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository <User, Long> {

    Optional<User> findUserByUsername(String username);
}
