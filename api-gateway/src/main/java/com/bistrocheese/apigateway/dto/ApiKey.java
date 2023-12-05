package com.bistrocheese.apigateway.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ApiKey {
    private String apiKey;
    private List<String> services;
}

