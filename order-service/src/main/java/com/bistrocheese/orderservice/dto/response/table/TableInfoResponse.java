package com.bistrocheese.orderservice.dto.response.table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableInfoResponse {
    private Long id;
    private Integer number;
    private Integer status;
}
