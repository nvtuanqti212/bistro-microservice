package com.bistrocheese.orderservice.repository;

import com.bistrocheese.orderservice.model.Order;
import com.bistrocheese.orderservice.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query(value = "select o from order o where o.staff_id = ?1 and o.table_id = ?2 and o.status = ?3", nativeQuery = true)
    Order findOrder(UUID id, Long table_id, OrderStatus status);
}
