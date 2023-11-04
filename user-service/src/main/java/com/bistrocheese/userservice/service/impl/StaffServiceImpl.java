package com.bistrocheese.userservice.service.impl;

import com.bistrocheese.userservice.dto.request.user.UserRequest;
import com.bistrocheese.userservice.model.user.Staff;
import com.bistrocheese.userservice.model.user.baseUser.User;
import com.bistrocheese.userservice.repository.StaffRepository;
import com.bistrocheese.userservice.service.StaffService;
import com.bistrocheese.userservice.service.UserService;
import com.bistrocheese.userservice.service.factory.StaffFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements UserService, StaffService {
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
    // UserService implementation End
}
