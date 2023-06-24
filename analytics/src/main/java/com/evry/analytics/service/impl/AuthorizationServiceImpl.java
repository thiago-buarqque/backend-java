package com.evry.analytics.service.impl;

import com.evry.analytics.entity.User;
import com.evry.analytics.repository.UserRepository;
import com.evry.analytics.repository.VisitorRepository;

import com.evry.analytics.security.exception.SecurityException;
import com.evry.analytics.service.AuthorizationService;
import com.evry.analytics.service.enums.UserRole;
import com.evry.analytics.service.security.JwtService;
import com.evry.analytics.service.security.dao.response.JwtAuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthorizationServiceImpl implements AuthorizationService {

    public AuthorizationServiceImpl(
            AuthenticationManager authenticationManager, JwtService jwtService,
            PasswordEncoder passwordEncoder, UserRepository userRepository,
            VisitorRepository visitorRepository) {

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public JwtAuthenticationResponse signup(User user) {
        if (!userRepository.findByEmail(user.getEmail()).isPresent()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            if(user.getRole() == null) {
                user.setRole(UserRole.ROLE_DEFAULT);
            }

            userRepository.save(user);

            return getJwtAuthenticationResponse(user);
        } else {
            throw new SecurityException("Email address is already in use.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public JwtAuthenticationResponse signIn(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new SecurityException(
                                "Invalid email/password.", HttpStatus.UNPROCESSABLE_ENTITY
                        ));

        return getJwtAuthenticationResponse(user);
    }

    private JwtAuthenticationResponse getJwtAuthenticationResponse(User user) {
        String token = jwtService.generateToken(user);

        return new JwtAuthenticationResponse(token);
    }

    private UUID getUUIDFromString(String userId) {
        return UUID.fromString(userId);
    }

}
