package lt.vtmc.example;

import lt.vtmc.example.models.ERole;
import lt.vtmc.example.models.Role;
import lt.vtmc.example.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StartRunner implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role userRole = new Role(ERole.ROLE_USER);
        Role adminRole = new Role(ERole.ROLE_ADMIN);
        this.roleRepository.save(userRole);
        this.roleRepository.save(adminRole);
    }
}
