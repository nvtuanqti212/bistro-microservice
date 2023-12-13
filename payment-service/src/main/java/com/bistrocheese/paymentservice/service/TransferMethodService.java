package com.bistrocheese.paymentservice.service;


import com.bistrocheese.paymentservice.dto.request.TransferMethodRequest;
import com.bistrocheese.paymentservice.model.TransferMethod;

import java.util.List;

public interface TransferMethodService {
    void create(TransferMethodRequest request);

    void update(Integer transferMethodId, TransferMethodRequest request);

    void delete(Integer transferMethodId);

    List<TransferMethod> getAll();

    TransferMethod getById(Integer transferMethodId);
}
