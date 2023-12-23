package com.bistrocheese.orderservice.client;

import com.bistrocheese.orderservice.dto.response.order.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

import static com.bistrocheese.orderservice.constant.ServiceConstant.USER_SERVICE;

@FeignClient(USER_SERVICE)
public interface UserFeignClient {
    @RequestMapping(value = "user-service/api/users/{userId}", method = RequestMethod.GET)
    Optional<UserResponse> getUser(@PathVariable String userId);
}
