package com.bistrocheese.userservice.service.impl;

import com.bistrocheese.userservice.constant.MessageConstant;
import com.bistrocheese.userservice.dto.request.user.UserRequest;
import com.bistrocheese.userservice.exception.BadRequestException;
import com.bistrocheese.userservice.model.user.Owner;
import com.bistrocheese.userservice.model.user.baseUser.User;
import com.bistrocheese.userservice.repository.OwnerRepository;
import com.bistrocheese.userservice.service.ManagerService;
import com.bistrocheese.userservice.service.OwnerService;
import com.bistrocheese.userservice.service.StaffService;
import com.bistrocheese.userservice.service.UserService;
import com.bistrocheese.userservice.service.factory.OwnerFactory;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements UserService, OwnerService {
    private final OwnerFactory ownerFactory;
    private final OwnerRepository ownerRepository;
    private final ManagerService managerService;
    private final StaffService staffService;
    private final Map<Integer, UserService> roleToServiceMap;

    @PostConstruct
    private void initRoleToServiceMap() {
        roleToServiceMap.put(1, this);
        roleToServiceMap.put(2, managerService);
        roleToServiceMap.put(3, staffService);
    }

    // UserService implementation Start
    @Override
    public Boolean isEmailExists(String email) {
        return ownerRepository.findByEmail(email).isPresent();
    }

    @Override
    public void saveUser(UserRequest userRequest) {
        ownerRepository.save((Owner) ownerFactory.create(userRequest));
    }
    // UserService implementation End

    // OwnerService implementation Start
    @Override
    public void createUser(UserRequest userRequest) {
        this.checkEmailExists(userRequest.getEmail());
        UserService userService = roleToServiceMap.get(userRequest.getRoleId());
        if (userService == null) {
            throw new BadRequestException(MessageConstant.INVALID_ROLE_ID);
        } else {
            userService.saveUser(userRequest);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        users.addAll(this.getOwners());
        users.addAll(managerService.getManagers());
        users.addAll(staffService.getStaffs());
        return users;
    }

    @Override
    public List<Owner> getOwners() {
        return ownerRepository.findAll();
    }
    // OwnerService implementation End

    private void checkEmailExists(String email) {
        UserService[] services = {this, managerService, staffService};
        for (UserService service : services) {
            if (service.isEmailExists(email)) {
                throw new BadRequestException(MessageConstant.EMAIL_ALREADY_EXISTS);
            }
        }
    }
}
