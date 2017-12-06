package uwr.onlinejudge.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uwr.onlinejudge.server.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
