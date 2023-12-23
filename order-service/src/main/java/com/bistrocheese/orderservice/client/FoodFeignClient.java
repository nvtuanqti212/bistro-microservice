package com.bistrocheese.orderservice.client;

import com.bistrocheese.orderservice.dto.response.FoodResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

import static com.bistrocheese.orderservice.constant.ServiceConstant.FOOD_SERVICE;

@FeignClient(FOOD_SERVICE)
public interface FoodFeignClient {
    @RequestMapping(value = "food-service/api/foods/{foodId}", method = RequestMethod.GET)
    ResponseEntity<FoodResponse> getDetailFood(@PathVariable("foodId") UUID foodId);
}
