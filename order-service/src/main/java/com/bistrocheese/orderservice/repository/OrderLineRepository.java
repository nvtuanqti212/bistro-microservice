package com.bistrocheese.orderservice.repository;

import com.bistrocheese.orderservice.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderLineRepository extends JpaRepository<OrderLine, UUID> {
    List<OrderLine> findByOrder_Id(UUID id);
}

