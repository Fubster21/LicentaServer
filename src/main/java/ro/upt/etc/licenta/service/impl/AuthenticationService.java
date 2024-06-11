package ro.upt.etc.licenta.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.upt.etc.licenta.repository.UserRepository;
import ro.upt.etc.licenta.repository.dto.auth.AuthenticationRequest;
import ro.upt.etc.licenta.repository.dto.auth.AuthenticationResponse;
import ro.upt.etc.licenta.repository.dto.auth.RegisterRequest;
import ro.upt.etc.licenta.repository.entity.User;
import ro.upt.etc.licenta.repository.entity.UserRole;
import ro.upt.etc.licenta.service.JwtService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private static final Logger sLogger = LoggerFactory.getLogger(AuthenticationService.class);

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.STANDARD)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        sLogger.debug("Trying to authenticate user: " + request.getEmail());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
//        sLogger.info("Authenticated user: " + request.getEmail());
        var user = repository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
