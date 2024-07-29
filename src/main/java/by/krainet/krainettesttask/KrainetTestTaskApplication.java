package by.krainet.krainettesttask;

import by.krainet.krainettesttask.common.Roles;
import by.krainet.krainettesttask.domain.Role;
import by.krainet.krainettesttask.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KrainetTestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(KrainetTestTaskApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName(Roles.USER.getName()).isEmpty() && roleRepository.findByName(Roles.ADMIN.getName()).isEmpty()) {
                roleRepository.save(Role.builder().name(Roles.ADMIN.getName()).build());
                roleRepository.save(Role.builder().name(Roles.USER.getName()).build());
            }
        };
    }
}
