package by.krainet.krainettesttask.service;

import by.krainet.krainettesttask.common.Roles;
import by.krainet.krainettesttask.domain.Role;
import by.krainet.krainettesttask.domain.User;
import by.krainet.krainettesttask.exception.EntityNotFoundException;
import by.krainet.krainettesttask.repository.RoleRepository;
import by.krainet.krainettesttask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(User user) {
        Role role = roleRepository.findByName(Roles.USER.getName())
                .orElseThrow(() -> new EntityNotFoundException(Role.class, Map.of("Role name", Roles.USER.getName())));

        user.setRoles(Set.of(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
