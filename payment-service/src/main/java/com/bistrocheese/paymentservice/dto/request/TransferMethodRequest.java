package com.bistrocheese.paymentservice.dto.request;

import com.bistrocheese.paymentservice.model.MethodType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferMethodRequest {
    @JsonProperty("method_type")
    private MethodType methodType;

    @JsonProperty("method_name")
    private String methodName;

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("account_holder_name")
    private String accountHolderName;

    @JsonProperty("is_active")
    private Boolean isActive;
}
