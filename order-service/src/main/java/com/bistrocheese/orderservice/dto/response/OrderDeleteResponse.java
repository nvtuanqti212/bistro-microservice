package com.bistrocheese.orderservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor(staticName = "of")
public class OrderDeleteResponse {
    @JsonProperty("id")
    private UUID id;
}
