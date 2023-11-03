package com.bistrocheese.userservice.service;

import com.bistrocheese.userservice.model.user.Manager;

import java.util.List;

public interface ManagerService extends UserService {
    List<Manager> getManagers();
}
