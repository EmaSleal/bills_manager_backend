package com.ventas.facturas.service.impl;



import com.ventas.facturas.config.JwtService;
import com.ventas.facturas.model.AuthenticationResponse;
import com.ventas.facturas.model.User;
import com.ventas.facturas.model.UserDto;
import com.ventas.facturas.repo.AuthenticationRepository;
import com.ventas.facturas.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationRepository authenticationRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public User register(User request) {
        log.info("Registering user {}", request);
        var userSecurity = User.builder()
                .userName(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(request.getRole())
                .enabled(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .accountNonLocked(true)
                .build();
        authenticationRepository.save(userSecurity);
        var jwtToken = jwtService.generateToken(userSecurity);
        return userSecurity;
    }

    @Override
    public UserDto authenticate(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = authenticationRepository.findByUserName(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var token = AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getRole(), user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(), token);
    }

    @Override
    public Optional<AuthenticationResponse> TokenforgotPassword(String email) {
        var user = authenticationRepository.findByEmail(email).orElse(null);
        if (user != null) {
            return Optional.of(AuthenticationResponse.builder()
                    .token(jwtService.generateTokenFP(user))
                    .build());
        }
        return Optional.empty();
    }

    @Override
    public Optional<AuthenticationResponse> resetPassword(User request) {
        var user = authenticationRepository.findByEmail(request.getEmail()).orElse(null);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            authenticationRepository.save(user);
            return Optional.of(AuthenticationResponse.builder()
                    .token(jwtService.generateToken(user))
                    .build());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<User>> getUsers() {
        return Optional.of(authenticationRepository.findAll());
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return authenticationRepository.findById(id);
    }
}
