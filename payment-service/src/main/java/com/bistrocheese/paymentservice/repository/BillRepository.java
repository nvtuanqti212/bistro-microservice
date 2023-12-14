package com.bistrocheese.paymentservice.repository;

import com.bistrocheese.paymentservice.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BillRepository extends JpaRepository<Bill, UUID> {
    Optional<Bill> findByOrderId(UUID orderId);
}
