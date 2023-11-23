package com.bistrocheese.userservice.controller;

import com.bistrocheese.userservice.constant.RouteConstant;
import com.bistrocheese.userservice.service.user.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(RouteConstant.STAFF)
public class StaffController {

    private final StaffService staffService;

    @GetMapping(RouteConstant.USER_ID)
    @ResponseStatus(HttpStatus.OK)
    public  boolean createOrder(@PathVariable String userId) {
        return staffService.getUserById(userId).isPresent();
    }
}
