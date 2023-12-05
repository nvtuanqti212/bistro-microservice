package com.bistrocheese.userservice.controller;

import com.bistrocheese.userservice.constant.MessageConstant;
import com.bistrocheese.userservice.constant.RouteConstant;
import com.bistrocheese.userservice.dto.request.user.UserRequest;
import com.bistrocheese.userservice.dto.response.MessageResponse;
import com.bistrocheese.userservice.dto.response.SuccessResponse;
import com.bistrocheese.userservice.model.user.baseUser.User;
import com.bistrocheese.userservice.service.order.OrderService;
import com.bistrocheese.userservice.service.user.OwnerService;
import com.bistrocheese.userservice.service.user.StaffService;
import com.bistrocheese.userservice.service.user.impl.OwnerServiceImpl;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.bistrocheese.userservice.constant.ServiceConstant.USER_SERVICE_BULKHEAD;

@RestController
@RequiredArgsConstructor
@RequestMapping(RouteConstant.USERS)
public class UserController {

    private final OwnerService ownerService;
    private final StaffService staffService;
    private final OrderService orderService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);



    @PostMapping()
    public ResponseEntity<MessageResponse> createUser(@RequestBody UserRequest userRequest) {
        ownerService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new MessageResponse(MessageConstant.CREATE_USER_SUCCESS)
        );
    }

    @GetMapping()
    public ResponseEntity<SuccessResponse<List<User>>> getAllUsers() throws InterruptedException {
        Thread.sleep(3000);
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

    //Place Order
    @GetMapping(RouteConstant.USER_ID)
    @ResponseStatus(HttpStatus.OK)
    public Optional<? extends User> getUser(@PathVariable String userId) {
        return staffService.getUserById(userId);
    }

    @PostMapping(RouteConstant.CREATE_ORDER)
    public ResponseEntity<MessageResponse> placeOrder(@PathVariable("userId") String userId) throws InterruptedException {
        String message = orderService.createOrder(userId).join();
        return ResponseEntity.ok(
                new MessageResponse(message)
        );
    }
}
