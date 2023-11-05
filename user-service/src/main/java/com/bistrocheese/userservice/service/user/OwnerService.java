package com.bistrocheese.userservice.service.user;

import com.bistrocheese.userservice.dto.request.user.UserRequest;
import com.bistrocheese.userservice.model.user.baseUser.User;

import java.util.List;

public interface OwnerService extends UserService{
    void createUser(UserRequest userRequest);
    List<User> getAllUsers();
    User getUserDetail(String userId);
    void deleteUser(String userId);
    void updateUser(String userId, UserRequest userRequest);
}
