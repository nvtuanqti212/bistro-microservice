package com.bistrocheese.authservice.auth;

import com.bistrocheese.authservice.entity.UserCredential;
import com.bistrocheese.authservice.repository.UserCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserCredentialRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            UserCredential userCredential = repository.findByEmail(email);
            return new CustomUserDetails(userCredential);
        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
