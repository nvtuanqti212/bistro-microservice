package com.bistrocheese.foodservice.controller;

import com.bistrocheese.foodservice.dto.request.FoodRequest;
import com.bistrocheese.foodservice.dto.response.FoodResponse;
import com.bistrocheese.foodservice.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FoodResponse createFood(@RequestBody FoodRequest request) {
        return foodService.createFood(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FoodResponse> getAllFoods() {
        return foodService.getAllFoods();
    }
}
