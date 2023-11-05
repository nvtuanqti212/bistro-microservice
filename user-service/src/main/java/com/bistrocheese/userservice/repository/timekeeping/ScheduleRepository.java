package com.bistrocheese.userservice.repository.timekeeping;

import com.bistrocheese.userservice.model.timekeeping.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.DayOfWeek;
import java.util.Optional;

public interface ScheduleRepository extends MongoRepository<Schedule, Integer> {
    Optional<Schedule> findByDayAndShift(DayOfWeek day, Integer shift);
}
