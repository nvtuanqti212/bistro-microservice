package com.bistrocheese.userservice.controller;

import com.bistrocheese.userservice.constant.MessageConstant;
import com.bistrocheese.userservice.constant.RouteConstant;
import com.bistrocheese.userservice.dto.request.user.UserRequest;
import com.bistrocheese.userservice.dto.response.MessageResponse;
import com.bistrocheese.userservice.dto.response.SuccessResponse;
import com.bistrocheese.userservice.model.user.baseUser.User;
import com.bistrocheese.userservice.service.user.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(RouteConstant.USERS)
public class UserController {
    private final OwnerService ownerService;

    @PostMapping()
    public ResponseEntity<MessageResponse> createUser(@RequestBody UserRequest userRequest) {
        ownerService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new MessageResponse(MessageConstant.CREATE_USER_SUCCESS)
        );
    }

    @GetMapping()
    public ResponseEntity<SuccessResponse<List<User>>> getAllUsers() {
        return ResponseEntity.ok(
                new SuccessResponse<>(
                        MessageConstant.GET_USERS_SUCCESS,
                        ownerService.getAllUsers()
                )
        );
    }

    @GetMapping(RouteConstant.USER_ID)
    public ResponseEntity<SuccessResponse<User>> getUserDetail(@PathVariable String userId) {
        return ResponseEntity.ok(
                new SuccessResponse<>(
                        MessageConstant.GET_USER_DETAIL_SUCCESS,
                        ownerService.getUserDetail(userId)
                )
        );
    }

    @DeleteMapping(RouteConstant.USER_ID)
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable String userId) {
        ownerService.deleteUser(userId);
        return ResponseEntity.ok(
                new MessageResponse(MessageConstant.DELETE_USER_SUCCESS)
        );
    }

    @PutMapping(RouteConstant.USER_ID)
    public ResponseEntity<MessageResponse> updateUser(@PathVariable String userId, @RequestBody UserRequest userRequest) {
        ownerService.updateUser(userId, userRequest);
        return ResponseEntity.ok(
                new MessageResponse(MessageConstant.UPDATE_USER_SUCCESS)
        );
    }
}