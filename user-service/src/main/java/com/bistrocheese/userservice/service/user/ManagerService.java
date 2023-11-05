package com.bistrocheese.userservice.service.user;

import com.bistrocheese.userservice.dto.request.timekeeping.ScheduleRequest;

public interface ManagerService extends UserService {
    void assignStaffToSchedule(String managerId, ScheduleRequest scheduleRequest);
}
