package com.bistrocheese.foodservice.dto.response;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
public class PagingFoodResponse {
    private List<?> data;
    private long totalResult;
    private int totalPage;
    private int currentPage;
}
