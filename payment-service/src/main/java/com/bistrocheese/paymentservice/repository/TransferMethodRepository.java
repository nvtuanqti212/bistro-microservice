package com.bistrocheese.paymentservice.repository;

import com.bistrocheese.paymentservice.model.TransferMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransferMethodRepository extends JpaRepository<TransferMethod, Integer> {
    Optional<TransferMethod> findByAccountNumber(String accountNumber);

    List<TransferMethod> findByIsActive(Boolean isActive);
}
