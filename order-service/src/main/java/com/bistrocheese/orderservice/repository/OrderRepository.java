package com.bistrocheese.orderservice.repository;

import com.bistrocheese.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional()
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findAllByStaffId(String staffId);
}
