package com.bistrocheese.apigateway;

import com.bistrocheese.apigateway.config.RedisHashComponent;
import com.bistrocheese.apigateway.dto.ApiKey;
import com.bistrocheese.apigateway.util.AppConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    private final RedisHashComponent redisHashComponent;

    public ApiGatewayApplication(RedisHashComponent redisHashComponent) {
        this.redisHashComponent = redisHashComponent;
    }

    @PostConstruct
    public void initKeysToRedis() {
        List<ApiKey> apiKeys = new ArrayList<>();

        apiKeys.add(new ApiKey(
                AppConstants.API_KEY,
                Stream.of(
                                AppConstants.USER_SERVICE_KEY,
                                AppConstants.ORDER_SERVICE_KEY,
                                AppConstants.FOOD_SERVICE_KEY,
                                AppConstants.AUTH_SERVICE_KEY,
                                AppConstants.PAYMENT_SERVICE_KEY
                        )
                        .collect(Collectors.toList())));
        List<Object> lists = redisHashComponent.hValues(AppConstants.RECORD_KEY);
        if (lists.size() != apiKeys.size()) {
            apiKeys.forEach(apiKey -> redisHashComponent.hSet(AppConstants.RECORD_KEY, apiKey.getApiKey(), apiKey));
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
