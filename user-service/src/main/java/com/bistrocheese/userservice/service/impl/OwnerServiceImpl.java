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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OwnerServiceImpl implements UserService, OwnerService {
    private final OwnerFactory ownerFactory;
    private final OwnerRepository ownerRepository;
    private final ManagerService managerService;
    private final StaffService staffService;

    private final Map<Integer, UserService> roleToServiceMap;
    private final UserService[] services;
    public OwnerServiceImpl(
            OwnerFactory ownerFactory,
            OwnerRepository ownerRepository,
            ManagerService managerService,
            StaffService staffService,
            Map<Integer, UserService> roleToServiceMap
    ) {
        this.ownerFactory = ownerFactory;
        this.ownerRepository = ownerRepository;
        this.managerService = managerService;
        this.staffService = staffService;
        this.roleToServiceMap = roleToServiceMap;
        this.services = new UserService[]{this, managerService, staffService};
    }

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

    @Override
    public List<User> getUsers() {
        return  new ArrayList<>(ownerRepository.findAll());
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
        for (UserService service : this.services) {
            users.addAll(service.getUsers());
        }
        return users;
    }
    // OwnerService implementation End

    private void checkEmailExists(String email) {
        for (UserService service : this.services) {
            if (service.isEmailExists(email)) {
                throw new BadRequestException(MessageConstant.EMAIL_ALREADY_EXISTS);
            }
        }
    }
}
