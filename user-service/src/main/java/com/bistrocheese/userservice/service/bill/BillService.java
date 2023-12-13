package com.bistrocheese.userservice.service.bill;

import com.bistrocheese.userservice.dto.request.bill.BillRequest;

public interface BillService {
    public void create(BillRequest billRequest);
}
