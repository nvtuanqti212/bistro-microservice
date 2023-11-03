package com.bistrocheese.userservice.service;

import com.bistrocheese.userservice.model.user.Staff;

import java.util.List;

public interface StaffService extends UserService{
    List<Staff> getStaffs();
}
