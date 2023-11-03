package com.bistrocheese.foodservice.repository;


import com.bistrocheese.foodservice.model.Category;
import com.bistrocheese.foodservice.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Transactional
public interface FoodRepo extends JpaRepository<Food, UUID>, JpaSpecificationExecutor<Food> {
    @Modifying
    @Query("""
            update Food f set
            f.name = ?1,
            f.category = ?2,
            f.description = ?3,
            f.productImage = ?4,
            f.price = ?5,
            f.lastModifiedDate = ?6,
            f.status = ?7
            where f.id = ?8
            """)
    void updateFoodById(String name, Category category, String description, String product_image,
                        BigDecimal price, Date lastModifiedDate,
                        Integer status, UUID id);

    @Modifying
    @Query("update Food f set f.status = ?1, f.lastModifiedDate = ?2 where f.id = ?3")
    void updateStatusAndLastModifiedDateById(Integer status, Date lastModifiedDate, UUID id);

    Optional<Food> findByName(String name);
}
