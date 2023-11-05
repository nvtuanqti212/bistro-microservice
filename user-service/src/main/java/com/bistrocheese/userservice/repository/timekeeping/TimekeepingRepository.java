package com.bistrocheese.userservice.repository.timekeeping;

import com.bistrocheese.userservice.model.timekeeping.Timekeeping;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TimekeepingRepository extends MongoRepository<Timekeeping, String> {
    Optional<Timekeeping> findByStaffIdAndScheduleId(String staffId, Integer scheduleId);
}
