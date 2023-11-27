package com.bistrocheese.userservice.service.user.impl;

import com.bistrocheese.userservice.dto.request.user.UserRequest;
import com.bistrocheese.userservice.model.user.Staff;
import com.bistrocheese.userservice.model.user.baseUser.User;
import com.bistrocheese.userservice.repository.user.StaffRepository;
import com.bistrocheese.userservice.service.user.StaffService;
import com.bistrocheese.userservice.service.user.factory.StaffFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final StaffFactory staffFactory;
    private final StaffRepository staffRepository;



    // UserService implementation Start
    @Override
    public Boolean isEmailExists(String email) {
        return staffRepository.findByEmail(email).isPresent();
    }

    @Override
    public void saveUser(UserRequest userRequest) {
        staffRepository.save((Staff) staffFactory.create(userRequest));
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(staffRepository.findAll());
    }

    @Override
    public Optional<? extends User> getUserById(String userId) {
        return staffRepository.findById(userId);
    }

    @Override
    public void deleteUserById(String userId) {
        staffRepository.deleteById(userId);
    }

    @Override
    public void updateUserById(User user, UserRequest userRequest) {
        staffRepository.save((Staff) staffFactory.update(user, userRequest));
    }


    // UserService implementation End
}
