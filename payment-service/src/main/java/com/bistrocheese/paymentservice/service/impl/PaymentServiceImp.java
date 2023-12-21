package com.bistrocheese.paymentservice.service.impl;


import com.bistrocheese.paymentservice.constant.APIStatus;
import com.bistrocheese.paymentservice.dto.request.PaymentRequest;
import com.bistrocheese.paymentservice.exception.CustomException;
import com.bistrocheese.paymentservice.model.Payment;
import com.bistrocheese.paymentservice.model.TransferMethod;
import com.bistrocheese.paymentservice.repository.PaymentRepository;
import com.bistrocheese.paymentservice.service.PaymentService;
import com.bistrocheese.paymentservice.service.TransferMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.bistrocheese.paymentservice.utils.DataUtils.copyProperties;


@Service
@RequiredArgsConstructor
public class PaymentServiceImp implements PaymentService {

    private final TransferMethodService transferMethodService;

    private final PaymentRepository paymentRepository;

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getById(UUID id) {
        return this.getPaymentById(id);
    }

    @Override
    public Payment create(PaymentRequest req) {
        Payment payment = copyProperties(req, Payment.class);

        TransferMethod transferMethod = transferMethodService.getById(req.getMethodId());
        payment.setMethodId(transferMethod);

        return paymentRepository.save(payment);
    }

    @Override
    public void update(UUID id, PaymentRequest req) {
        Payment payment = this.getPaymentById(id);
        payment.setCustomerName(req.getCustomerName());
        payment.setPhoneNumber(req.getPhoneNumber());
        paymentRepository.save(payment);
    }

    @Override
    public void delete(UUID id) {
        Payment payment = this.getPaymentById(id);
        paymentRepository.delete(payment);
    }

    private Payment getPaymentById(UUID id) {
        return paymentRepository.findById(id).orElseThrow(
                () -> new CustomException(APIStatus.PAYMENT_NOT_FOUND)
        );
    }
}
