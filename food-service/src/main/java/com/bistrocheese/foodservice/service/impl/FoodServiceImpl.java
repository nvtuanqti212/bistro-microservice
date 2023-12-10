package com.bistrocheese.foodservice.service.impl;

import com.bistrocheese.foodservice.constant.APIStatus;
import com.bistrocheese.foodservice.dto.request.FoodRequest;
import com.bistrocheese.foodservice.dto.response.FoodResponse;
import com.bistrocheese.foodservice.exception.CustomException;
import com.bistrocheese.foodservice.model.Category;
import com.bistrocheese.foodservice.model.Food;
import com.bistrocheese.foodservice.repository.CategoryRepo;
import com.bistrocheese.foodservice.repository.FoodRepository;
import com.bistrocheese.foodservice.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final CategoryRepo categoryRepository;
    @Override
    public List<FoodResponse> getAllFoods() {
        List<Food> foodList = foodRepository.findAll();
        Comparator<Food> foodComparator = Comparator.comparing(
                food -> food.getCategory().getId()
        );
        foodList.sort(foodComparator);

        return foodList.stream().map(this::mapToFoodResponse).toList();
    }

    @Override
    public FoodResponse createFood(FoodRequest request) {
        Category category = categoryRepository.findById(request.getCategory()).orElseThrow(
                () -> new CustomException(APIStatus.CATEGORY_NOT_FOUND));
        Optional<Food> optionalFood = foodRepository.findByName(request.getName());
        if (optionalFood.isPresent()) {
            throw new CustomException(APIStatus.FOOD_ALREADY_EXISTED);
        }
        var newFood = Food.builder()
                .name(request.getName())
                .category(category)
                .description(request.getDescription())
                .price(request.getPrice())
                .image(request.getImage())
                .status(request.getStatus())
                .build();


        return FoodResponse.builder()
                .id(foodRepository.save(newFood).getId())
                .build();
    }

    @Override
    public void deleteFood(UUID foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow(
                () -> new CustomException(APIStatus.FOOD_NOT_FOUND)
        );
        foodRepository.delete(food);
    }

    @Override
    public void updateFood(UUID foodId, FoodRequest updatingFood) {
        Food existingFood = foodRepository.findById(foodId).orElseThrow(
                () -> new CustomException(APIStatus.FOOD_NOT_FOUND)
        );
        Category updateCategory = categoryRepository.findById(updatingFood.getCategory()).orElseThrow(
                () -> new CustomException(APIStatus.CATEGORY_NOT_FOUND)
        );
        copyProperties(updatingFood, existingFood);
        foodRepository.save(existingFood);
    }

    @Override
    public FoodResponse getDetailFood(UUID foodId) {
        return mapToFoodResponse(foodRepository.findById(foodId).orElseThrow(
                () -> new CustomException(APIStatus.FOOD_NOT_FOUND)
        ));
    }

    private FoodResponse mapToFoodResponse(Food product) {
        return FoodResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .foodImage(product.getImage())
                .price(product.getPrice())
                .status(product.getStatus())
                .build();
    }
}
