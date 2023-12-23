package com.bistrocheese.userservice.controller;

import com.bistrocheese.userservice.constant.MessageConstant;
import com.bistrocheese.userservice.constant.RouteConstant;
import com.bistrocheese.userservice.dto.request.user.UserRequest;
import com.bistrocheese.userservice.dto.response.MessageResponse;
import com.bistrocheese.userservice.dto.response.SuccessResponse;
import com.bistrocheese.userservice.model.user.baseUser.User;
import com.bistrocheese.userservice.service.user.OwnerService;
import com.bistrocheese.userservice.service.user.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(RouteConstant.USERS)
public class UserController {

    private final OwnerService ownerService;
    private final StaffService staffService;

    @PostMapping()
    public ResponseEntity<MessageResponse> createUser(@RequestBody UserRequest userRequest) {
        ownerService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new MessageResponse(MessageConstant.CREATE_USER_SUCCESS)
        );
    }

    @GetMapping()
    public ResponseEntity<SuccessResponse<List<User>>> getAllUsers() throws InterruptedException {
        return ResponseEntity.ok(
                new SuccessResponse<>(
                        MessageConstant.GET_USERS_SUCCESS,
                        ownerService.getAllUsers()
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

    @GetMapping(RouteConstant.USER_ID)
    @ResponseStatus(HttpStatus.OK)
    public Optional<? extends User> getUser(@PathVariable String userId) {
        return staffService.getUserById(userId);
    }
}
