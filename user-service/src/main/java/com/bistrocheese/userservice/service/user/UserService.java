package com.bistrocheese.userservice.service.user;

import com.bistrocheese.userservice.dto.request.user.UserRequest;
import com.bistrocheese.userservice.model.user.baseUser.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Boolean isEmailExists(String email);
    void saveUser(UserRequest userRequest);
    List<User> getUsers();
    Optional<? extends User> getUserById(String userId);
    void deleteUserById(String userId);
    void updateUserById(User user, UserRequest userRequest);
}
