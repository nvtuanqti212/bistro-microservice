package com.bistrocheese.paymentservice.service.impl;


import com.bistrocheese.paymentservice.constant.APIStatus;
import com.bistrocheese.paymentservice.dto.request.TransferMethodRequest;
import com.bistrocheese.paymentservice.exception.CustomException;
import com.bistrocheese.paymentservice.model.TransferMethod;
import com.bistrocheese.paymentservice.repository.TransferMethodRepository;
import com.bistrocheese.paymentservice.service.TransferMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bistrocheese.paymentservice.utils.DataUtils.copyProperties;


@Service
@RequiredArgsConstructor
public class TransferMethodServiceImpl implements TransferMethodService {
    private final TransferMethodRepository repository;

    @Override
    public void create(TransferMethodRequest request) {
        TransferMethod newTransferMethod = copyProperties(request, TransferMethod.class);
        repository.save(newTransferMethod);
    }

    @Override
    public void update(Integer transferMethodId, TransferMethodRequest req) {
        TransferMethod transferMethod = getTransferMethod(transferMethodId);
        transferMethod.setAccountNumber(req.getAccountNumber());
        transferMethod.setAccountHolderName(req.getAccountHolderName());
        transferMethod.setIsActive(req.getIsActive());
        repository.save(transferMethod);
    }

    @Override
    public void delete(Integer transferMethodId) {
        TransferMethod transferMethod = this.getById(transferMethodId);
        repository.delete(transferMethod);
    }

    @Override
    public List<TransferMethod> getAll() {

        //TODO: Implement this method
//        Integer loginRole = userDetailService.getRoleLogin();
//        if (loginRole == 2) {
//            return repository.findByIsActive(true);
//        }
//        return repository.findAll();
        return null;
    }

    @Override
    public TransferMethod getById(Integer transferMethodId) {
        return this.getTransferMethod(transferMethodId);
    }

    private TransferMethod getTransferMethod(Integer transferMethodId) {
        return repository.findById(transferMethodId).orElseThrow(
                () -> new CustomException(APIStatus.TRANSFER_METHOD_NOT_FOUND)
        );
    }
}
