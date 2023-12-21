package com.bistrocheese.authservice.service;

import com.bistrocheese.authservice.client.UserFeignClient;
import com.bistrocheese.authservice.dto.AuthRequest;
import com.bistrocheese.authservice.dto.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserFeignClient userFeignClient;

    public ResponseEntity<MessageResponse> createUser(AuthRequest request) {
        return userFeignClient.createUser(request);
    }
}
