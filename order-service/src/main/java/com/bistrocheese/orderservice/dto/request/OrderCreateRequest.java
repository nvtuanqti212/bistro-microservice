package com.bistrocheese.orderservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class OrderCreateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    @JsonProperty("staffId")
    private String staffId;
    @JsonProperty("order_lines")
    private List<OrderLineRequest> orderLines;
}
