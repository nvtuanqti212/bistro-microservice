package com.bistrocheese.userservice.repository.user;

import com.bistrocheese.userservice.model.user.Owner;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OwnerRepository extends MongoRepository<Owner, String> {
    Optional<Owner> findByEmail(String email);
}
