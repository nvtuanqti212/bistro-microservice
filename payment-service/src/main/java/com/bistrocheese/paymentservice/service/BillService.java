package com.bistrocheese.paymentservice.service;


import com.bistrocheese.paymentservice.dto.request.BillRequest;

public interface BillService {
    void create(BillRequest billRequest);
}
