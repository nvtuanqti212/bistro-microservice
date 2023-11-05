package com.bistrocheese.userservice.repository.user;

import com.bistrocheese.userservice.model.user.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StaffRepository extends MongoRepository<Staff, String> {
    Optional<Staff> findByEmail(String email);
}
