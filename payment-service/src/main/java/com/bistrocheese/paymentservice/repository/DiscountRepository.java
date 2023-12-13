package com.bistrocheese.paymentservice.repository;


import com.bistrocheese.paymentservice.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
}
