package com.bistrocheese.userservice.service.impl;

import com.bistrocheese.userservice.constant.MessageConstant;
import com.bistrocheese.userservice.dto.request.user.UserRequest;
import com.bistrocheese.userservice.exception.BadRequestException;
import com.bistrocheese.userservice.model.user.Staff;
import com.bistrocheese.userservice.repository.StaffRepository;
import com.bistrocheese.userservice.service.StaffService;
import com.bistrocheese.userservice.service.UserService;
import com.bistrocheese.userservice.service.factory.StaffFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    // UserService implementation End

    @Override
    public List<Staff> getStaffs() {
        return staffRepository.findAll();
    }
}
