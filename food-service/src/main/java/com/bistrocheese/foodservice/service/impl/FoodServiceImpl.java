package com.bistrocheese.foodservice.service.impl;

import com.bistrocheese.foodservice.constant.APIStatus;
import com.bistrocheese.foodservice.dto.request.FoodRequest;
import com.bistrocheese.foodservice.dto.response.FoodResponse;
import com.bistrocheese.foodservice.dto.response.PagingFoodResponse;
import com.bistrocheese.foodservice.exception.CustomException;
import com.bistrocheese.foodservice.model.Category;
import com.bistrocheese.foodservice.model.Food;
import com.bistrocheese.foodservice.repository.CategoryRepo;
import com.bistrocheese.foodservice.repository.FoodRepo;
import com.bistrocheese.foodservice.repository.specification.FoodSpecification;
import com.bistrocheese.foodservice.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepo foodRepository;
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
                .productImage(request.getProductImage())
                .createdDate(new Date())
                .lastModifiedDate(new Date())
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
        Date lastModifiedDate = new Date();
        foodRepository.updateFoodById(
                updatingFood.getName(),
                updateCategory,
                updatingFood.getDescription(),
                updatingFood.getProductImage(),
                updatingFood.getPrice(),
                lastModifiedDate,
                updatingFood.getStatus(),
                existingFood.getId()
        );
    }

    @Override
    public FoodResponse getDetailFood(UUID foodId) {
        return mapToFoodResponse(foodRepository.findById(foodId).orElseThrow(
                () -> new CustomException(APIStatus.FOOD_NOT_FOUND)
        ));
    }

    @Override
    public PagingFoodResponse searchFood(String category, String searchKey, BigDecimal minPrice, BigDecimal maxPrice, Integer sortCase, Boolean isAscSort, Integer pageNumber, Integer pageSize) {
        if (pageSize < 1 || pageNumber < 1) {
            throw new CustomException(APIStatus.ERR_PAGINATION);
        }
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Food> foodPage = foodRepository.findAll(
                new FoodSpecification(
                        category,
                        searchKey,
                        minPrice,
                        maxPrice,
                        sortCase,
                        isAscSort
                ), pageable);
        return new PagingFoodResponse(
                foodPage.getContent(),
                foodPage.getTotalElements(),
                foodPage.getTotalPages(),
                foodPage.getNumber() + 1
        );
    }
    private FoodResponse mapToFoodResponse(Food product) {
        return FoodResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .foodImage(product.getProductImage())
                .price(product.getPrice())
                .status(product.getStatus())
                .build();
    }
}
