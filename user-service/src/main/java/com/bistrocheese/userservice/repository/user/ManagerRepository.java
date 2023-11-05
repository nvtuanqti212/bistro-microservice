package com.bistrocheese.userservice.repository.user;

import com.bistrocheese.userservice.model.user.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ManagerRepository extends MongoRepository<Manager, String> {
    Optional<Manager> findByEmail(String email);
}
