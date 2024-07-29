package by.krainet.krainettesttask;

import by.krainet.krainettesttask.common.Roles;
import by.krainet.krainettesttask.domain.Role;
import by.krainet.krainettesttask.domain.User;
import by.krainet.krainettesttask.repository.RoleRepository;
import by.krainet.krainettesttask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class KrainetTestTaskApplication {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(KrainetTestTaskApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            if (roleRepository.findByName(Roles.USER.getName()).isEmpty() && roleRepository.findByName(Roles.ADMIN.getName()).isEmpty()) {
                roleRepository.save(Role.builder().name(Roles.ADMIN.getName()).build());
                roleRepository.save(Role.builder().name(Roles.USER.getName()).build());
            }

            if (userRepository.findByUsername(Roles.ADMIN.getName()).isEmpty()) {
                Optional<Role> role = roleRepository.findByName(Roles.ADMIN.getName());
                User user = User
                        .builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(Set.of(role.get()))
                        .build();

                userRepository.save(user);
            }
        };
    }
}
