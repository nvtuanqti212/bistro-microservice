package com.bistrocheese.userservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagingResponse {
    private List<?> data;
    private long totalResult;
    private int totalPage;
    private int currentPage;
}
