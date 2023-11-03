package com.bistrocheese.orderservice.repository;

import com.bistrocheese.orderservice.model.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
