package com.bistrocheese.userservice.service.impl;

import com.bistrocheese.userservice.dto.request.user.UserRequest;
import com.bistrocheese.userservice.model.user.Manager;
import com.bistrocheese.userservice.model.user.baseUser.User;
import com.bistrocheese.userservice.repository.ManagerRepository;
import com.bistrocheese.userservice.service.ManagerService;
import com.bistrocheese.userservice.service.UserService;
import com.bistrocheese.userservice.service.factory.ManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements UserService, ManagerService {
    private final ManagerFactory managerFactory;
    private final ManagerRepository managerRepository;

    // UserService implementation Start
    @Override
    public Boolean isEmailExists(String email) {
        return managerRepository.findByEmail(email).isPresent();
    }

    @Override
    public void saveUser(UserRequest userRequest) {
        managerRepository.save((Manager) managerFactory.create(userRequest));
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(managerRepository.findAll());
    }
    // UserService implementation End
}
