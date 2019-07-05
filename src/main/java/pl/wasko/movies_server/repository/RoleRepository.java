package pl.wasko.movies_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wasko.movies_server.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
