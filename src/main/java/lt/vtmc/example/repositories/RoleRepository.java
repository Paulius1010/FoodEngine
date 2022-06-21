package lt.vtmc.example.repositories;

import lt.vtmc.example.models.ERole;
import lt.vtmc.example.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

}
