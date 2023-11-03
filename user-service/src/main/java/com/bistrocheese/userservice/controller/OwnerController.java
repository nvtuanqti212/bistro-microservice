package com.bistrocheese.userservice.controller;

import com.bistrocheese.userservice.constant.MessageConstant;
import com.bistrocheese.userservice.constant.RouteConstant;
import com.bistrocheese.userservice.dto.request.user.UserRequest;
import com.bistrocheese.userservice.dto.response.MessageResponse;
import com.bistrocheese.userservice.dto.response.SuccessResponse;
import com.bistrocheese.userservice.model.user.baseUser.User;
import com.bistrocheese.userservice.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(RouteConstant.OWNER)
public class OwnerController {
    private final OwnerService ownerService;

    @PostMapping(RouteConstant.USERS)
    public ResponseEntity<MessageResponse> createUser(@RequestBody UserRequest userRequest) {
        ownerService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new MessageResponse(MessageConstant.CREATE_USER_SUCCESS)
        );
    }

    @GetMapping(RouteConstant.USERS)
    public ResponseEntity<SuccessResponse<List<User>>> getAllUsers() {
        return ResponseEntity.ok(
                new SuccessResponse<>(
                        MessageConstant.GET_USERS_SUCCESS,
                        ownerService.getAllUsers()
                )
        );
    }

//    @GetMapping(RouteConstant.USER_ID)
//    public ResponseEntity<MessageResponse> getUserById(@PathVariable String userId) {
//        return ResponseEntity.ok(
//                new SuccessResponse<>(
//                        MessageConstant.GET_USER_SUCCESS,
//                        ownerService.getUserById(userId)
//                )
//        );
//    }
}
