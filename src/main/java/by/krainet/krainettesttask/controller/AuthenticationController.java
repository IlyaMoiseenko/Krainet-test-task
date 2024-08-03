package by.krainet.krainettesttask.controller;

import by.krainet.krainettesttask.domain.User;
import by.krainet.krainettesttask.dto.request.AuthenticationRequest;
import by.krainet.krainettesttask.dto.request.RegistrationRequest;
import by.krainet.krainettesttask.dto.response.AuthenticationResponse;
import by.krainet.krainettesttask.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) {
        User user = User
                .builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();

        authenticationService.register(user);

        return ResponseEntity.accepted().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
