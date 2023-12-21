package com.bistrocheese.authservice.service;

import com.bistrocheese.authservice.dto.AuthRequest;
import com.bistrocheese.authservice.entity.UserCredential;
import com.bistrocheese.authservice.repository.UserCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserCredentialRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserService userService;

    public UserCredential saveUser(AuthRequest request) {
        userService.createUser(request);
        UserCredential userCredential = UserCredential.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        return repository.save(userCredential);
    }

    public String generateToken(String email) {
        return jwtService.generateToken(email);
    }

    public void validateToken (String token) {
        jwtService.validateToken(token);
    }
}
