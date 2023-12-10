package com.bistrocheese.foodservice.repository;

import com.bistrocheese.foodservice.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Transactional
@Repository
public interface FoodRepository extends JpaRepository<Food, UUID>{
    Optional<Food> findByName(String name);
}
