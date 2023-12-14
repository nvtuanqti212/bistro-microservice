package com.bistrocheese.paymentservice.service;

import com.bistrocheese.paymentservice.dto.request.PaymentRequest;
import com.bistrocheese.paymentservice.model.Payment;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    List<Payment> getAll();

    Payment getById(UUID id);

    Payment create(PaymentRequest req);

    void update(UUID id, PaymentRequest req);

    void delete(UUID id);
}
