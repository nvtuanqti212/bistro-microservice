package com.bistrocheese.userservice.service.user.factory;

import com.bistrocheese.userservice.dto.request.user.UserRequest;
import com.bistrocheese.userservice.model.user.Owner;
import com.bistrocheese.userservice.model.user.baseUser.User;
import org.springframework.stereotype.Component;

@Component
public class OwnerFactory extends UserFactory {
    @Override
    protected User createUser(User user, UserRequest userRequest) {
        return new Owner(
                user,
                userRequest.getBranch(),
                userRequest.getLicenseBusiness()
        );
    }

    @Override
    protected User updateUser(User user, UserRequest userRequest) {
        return new Owner(
                user,
                userRequest.getBranch(),
                userRequest.getLicenseBusiness()
        );
    }
}
