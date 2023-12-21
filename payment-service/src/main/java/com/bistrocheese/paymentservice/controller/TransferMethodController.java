package com.bistrocheese.paymentservice.controller;

import com.bistrocheese.paymentservice.constant.APIConstant;
import com.bistrocheese.paymentservice.constant.MessageConstant;
import com.bistrocheese.paymentservice.dto.request.TransferMethodRequest;
import com.bistrocheese.paymentservice.dto.response.APIResponse;
import com.bistrocheese.paymentservice.dto.response.MessageResponse;
import com.bistrocheese.paymentservice.model.TransferMethod;
import com.bistrocheese.paymentservice.service.TransferMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIConstant.PAYMENT_METHODS)
public class TransferMethodController {
    private final TransferMethodService transferMethodService;

    @PostMapping()
    public ResponseEntity<MessageResponse> createTransferMethod(@RequestBody TransferMethodRequest request) {
        transferMethodService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new MessageResponse(MessageConstant.CREATE_TRANSFER_METHOD_SUCCESS)
        );
    }

    @GetMapping()
    public ResponseEntity<APIResponse<List<TransferMethod>>> getAllTransferMethod() {
        return ResponseEntity.ok(
                new APIResponse<>(
                        MessageConstant.GET_ALL_TRANSFER_METHOD_SUCCESS,
                        transferMethodService.getAll())
        );
    }

    @GetMapping(APIConstant.ID)
    public ResponseEntity<APIResponse<TransferMethod>> getTransferMethodById(
            @PathVariable("id") Integer transferMethodId
    ) {
        return ResponseEntity.ok(
                new APIResponse<>(
                        MessageConstant.GET_TRANSFER_METHOD_BY_ID_SUCCESS,
                        transferMethodService.getById(transferMethodId))
        );
    }

    @PutMapping(APIConstant.ID)
    public ResponseEntity<MessageResponse> updateTransferMethod(
            @PathVariable("id") Integer transferMethodId,
            @RequestBody TransferMethodRequest request
    ) {
        transferMethodService.update(transferMethodId, request);
        return ResponseEntity.ok(
                new MessageResponse(MessageConstant.UPDATE_TRANSFER_METHOD_SUCCESS)
        );
    }

    @DeleteMapping(APIConstant.ID)
    public ResponseEntity<MessageResponse> deleteTransferMethod(
            @PathVariable("id") Integer transferMethodId
    ) {
        transferMethodService.delete(transferMethodId);
        return ResponseEntity.ok(
                new MessageResponse(MessageConstant.DELETE_TRANSFER_METHOD_SUCCESS)
        );
    }
}
