package com.bistrocheese.userservice.repository.user;

import com.bistrocheese.userservice.model.user.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends MongoRepository<Owner, String> {
    Optional<Owner> findByEmail(String email);
}
