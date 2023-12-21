package com.bistrocheese.authservice.client;

import com.bistrocheese.authservice.dto.AuthRequest;
import com.bistrocheese.authservice.dto.MessageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("userservice")
public interface UserFeignClient {
    @RequestMapping(value = "user-service/api/users", method = RequestMethod.POST)
    ResponseEntity<MessageResponse> createUser(@RequestBody AuthRequest authRequest);
}
