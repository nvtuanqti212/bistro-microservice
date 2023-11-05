package com.bistrocheese.userservice.service.user.impl;

import com.bistrocheese.userservice.constant.MessageConstant;
import com.bistrocheese.userservice.dto.request.timekeeping.ScheduleRequest;
import com.bistrocheese.userservice.dto.request.user.UserRequest;
import com.bistrocheese.userservice.exception.BadRequestException;
import com.bistrocheese.userservice.model.timekeeping.Schedule;
import com.bistrocheese.userservice.model.user.Manager;
import com.bistrocheese.userservice.model.user.baseUser.User;
import com.bistrocheese.userservice.repository.user.ManagerRepository;
import com.bistrocheese.userservice.repository.timekeeping.ScheduleRepository;
import com.bistrocheese.userservice.repository.timekeeping.TimekeepingRepository;
import com.bistrocheese.userservice.service.user.ManagerService;
import com.bistrocheese.userservice.service.user.UserService;
import com.bistrocheese.userservice.service.user.factory.ManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements UserService, ManagerService {
    private final ManagerFactory managerFactory;
    private final ManagerRepository managerRepository;
    private final TimekeepingRepository timekeepingRepository;
    private final ScheduleRepository scheduleRepository;

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

    // ManagerService implementation Start
    @Override
    public void assignStaffToSchedule(String managerId, ScheduleRequest scheduleRequest) {
        Manager manager = (Manager) this.getUserById(managerId).orElseThrow(
                () -> new BadRequestException(MessageConstant.USER_NOT_FOUND)
        );
        Optional<Schedule> schedule = scheduleRepository.findByDayAndShift(
                scheduleRequest.getDayOfWeek(),
                scheduleRequest.getShift()
        );
    }
}
