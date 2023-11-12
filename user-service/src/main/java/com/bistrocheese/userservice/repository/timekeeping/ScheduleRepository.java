package com.bistrocheese.userservice.repository.timekeeping;

import com.bistrocheese.userservice.model.timekeeping.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScheduleRepository extends MongoRepository<Schedule, Integer> {
}
