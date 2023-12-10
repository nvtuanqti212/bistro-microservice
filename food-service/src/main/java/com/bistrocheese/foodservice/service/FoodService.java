package com.bistrocheese.foodservice.service;

import com.bistrocheese.foodservice.dto.request.FoodRequest;
import com.bistrocheese.foodservice.dto.response.FoodResponse;
import com.bistrocheese.foodservice.dto.response.PagingFoodResponse;
import com.bistrocheese.foodservice.model.Food;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface FoodService {

    //Implement Product
    List<FoodResponse> getAllFoods();

    FoodResponse createFood(FoodRequest request);

    void deleteFood(UUID foodId);

    void updateFood(UUID foodId, FoodRequest updatingFood);

    FoodResponse getDetailFood(UUID foodId);
}