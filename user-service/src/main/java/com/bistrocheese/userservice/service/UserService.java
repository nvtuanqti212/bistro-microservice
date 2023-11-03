package com.bistrocheese.userservice.service;

import com.bistrocheese.userservice.dto.request.user.UserRequest;
import com.bistrocheese.userservice.model.user.baseUser.User;

import java.util.List;

public interface UserService {
    Boolean isEmailExists(String email);
    void saveUser(UserRequest userRequest);
//    List<User> getUsers();
}
