package com.bistrocheese.authservice.controller;

import com.bistrocheese.authservice.dto.AuthRequest;
import com.bistrocheese.authservice.dto.LoginResponse;
import com.bistrocheese.authservice.entity.UserCredential;
import com.bistrocheese.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth-service/api/auth")
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserCredential> register(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(
                authService.saveUser(request)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String token;
        if (!authentication.isAuthenticated()) {
            throw new RuntimeException("Authentication failed");
        } else {
            token = authService.generateToken(request.getEmail());
        }

        return ResponseEntity.ok(
               LoginResponse.builder()
                       .message("Login successfully")
                       .token(token)
                       .email(request.getEmail())
                       .build()
        );
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validate(@RequestParam String token) {
        try {
            authService.validateToken(token);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Token ");
        }
        return ResponseEntity.ok("Valid Token");
    }
}
