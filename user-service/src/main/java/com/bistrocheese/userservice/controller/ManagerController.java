package com.bistrocheese.userservice.controller;

import com.bistrocheese.userservice.constant.MessageConstant;
import com.bistrocheese.userservice.constant.RouteConstant;
import com.bistrocheese.userservice.dto.request.timekeeping.ScheduleRequest;
import com.bistrocheese.userservice.dto.response.MessageResponse;
import com.bistrocheese.userservice.service.user.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(RouteConstant.MANAGERS)
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping(RouteConstant.SCHEDULES)
    public ResponseEntity<MessageResponse> assignStaffToSchedule(
            @PathVariable String managerId,
            @RequestBody ScheduleRequest scheduleRequest
    ) {
        managerService.assignStaffToSchedule(managerId, scheduleRequest);
       return ResponseEntity.status(HttpStatus.CREATED).body(
                new MessageResponse(MessageConstant.ASSIGN_STAFF_TO_SCHEDULE_SUCCESS)
       );
    }
}
