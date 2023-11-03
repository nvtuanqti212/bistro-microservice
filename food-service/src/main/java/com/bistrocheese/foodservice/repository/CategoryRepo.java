package com.bistrocheese.foodservice.repository;

import com.bistrocheese.foodservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String name);
}
