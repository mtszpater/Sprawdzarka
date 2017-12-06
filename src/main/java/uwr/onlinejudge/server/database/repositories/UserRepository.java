package uwr.onlinejudge.server.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uwr.onlinejudge.server.database.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);

    User findByEmail(String email);
}
