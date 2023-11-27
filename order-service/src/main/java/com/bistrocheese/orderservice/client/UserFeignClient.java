package com.bistrocheese.orderservice.client;

import com.bistrocheese.orderservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("userservice")
public interface UserFeignClient {
    @RequestMapping(value = "api/v1/users/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable("userId") String staffId);
}
