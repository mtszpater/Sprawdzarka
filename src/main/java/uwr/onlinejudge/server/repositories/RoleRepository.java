package uwr.onlinejudge.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uwr.onlinejudge.server.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
