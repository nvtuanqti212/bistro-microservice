package com.bistrocheese.userservice.service.user.impl;

import com.bistrocheese.userservice.dto.request.user.UserRequest;
import com.bistrocheese.userservice.model.user.Manager;
import com.bistrocheese.userservice.model.user.baseUser.User;
import com.bistrocheese.userservice.repository.user.ManagerRepository;
import com.bistrocheese.userservice.service.user.ManagerService;
import com.bistrocheese.userservice.service.user.factory.ManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
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

    @Override
    public Optional<? extends User> getUserById(String userId) {
        return managerRepository.findById(userId);
    }

    @Override
    public void deleteUserById(String userId) {
        managerRepository.deleteById(userId);
    }

    @Override
    public void updateUserById(User user, UserRequest userRequest) {
        managerRepository.save((Manager) managerFactory.update(user, userRequest));
    }
    // UserService implementation End
}
