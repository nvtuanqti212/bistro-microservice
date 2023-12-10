package com.bistrocheese.userservice.client;

import com.bistrocheese.userservice.dto.response.FoodResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@FeignClient("foodservice")
public interface FoodFeignClient {
    //TODO: Fix this
    @RequestMapping(value = "food-service/api/foods/{foodId}", method = RequestMethod.GET)
    ResponseEntity<FoodResponse> getDetailFood(@PathVariable("foodId") UUID foodId);
}
