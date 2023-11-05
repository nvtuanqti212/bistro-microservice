package com.bistrocheese.userservice.repository.user;

import com.bistrocheese.userservice.model.user.baseUser.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, Integer> {
}
