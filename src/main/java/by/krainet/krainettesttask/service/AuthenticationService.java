package by.krainet.krainettesttask.service;

import by.krainet.krainettesttask.common.Roles;
import by.krainet.krainettesttask.configuration.security.JwtService;
import by.krainet.krainettesttask.domain.Role;
import by.krainet.krainettesttask.domain.User;
import by.krainet.krainettesttask.dto.request.AuthenticationRequest;
import by.krainet.krainettesttask.dto.response.AuthenticationResponse;
import by.krainet.krainettesttask.exception.EntityNotFoundException;
import by.krainet.krainettesttask.repository.RoleRepository;
import by.krainet.krainettesttask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public void register(User user) {
        Role role = roleRepository.findByName(Roles.USER.getName())
                .orElseThrow(() -> new EntityNotFoundException(Role.class, Map.of("Role name", Roles.USER.getName())));

        user.setRoles(Set.of(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        var claims = new HashMap<String, Object>();
        var user = (User) auth.getPrincipal();
        var jwtToken = jwtService.generateToken(claims, user);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}
