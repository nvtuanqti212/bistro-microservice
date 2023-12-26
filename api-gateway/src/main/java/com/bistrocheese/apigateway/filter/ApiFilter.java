package com.bistrocheese.apigateway.filter;

import com.bistrocheese.apigateway.config.RedisHashComponent;
import com.bistrocheese.apigateway.dto.ApiKey;
import com.bistrocheese.apigateway.util.AppConstants;
import com.bistrocheese.apigateway.util.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class ApiFilter implements GlobalFilter, Ordered {
    private final RedisHashComponent redisHashComponent;

    public ApiFilter(RedisHashComponent redisHashComponent) {
        this.redisHashComponent = redisHashComponent;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        List<String> apiKeysHeader = exchange.getRequest().getHeaders().get("api-key");
        log.info("apiKeysHeader: {}", apiKeysHeader);

        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        String routeId = route != null ? route.getId() : null;

        if (Objects.equals(routeId, "eurekaservice") || Objects.equals(routeId, "eurekaservice-static")) {
            return chain.filter(exchange);
        }

        if (routeId == null || CollectionUtils.isEmpty(apiKeysHeader) || !isAuthorized(apiKeysHeader.get(0), routeId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "you can not access this service, please check your api key");
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    private boolean isAuthorized(String apiKey, String routeId) {
        Object apiKeyObject = redisHashComponent.hGet(AppConstants.RECORD_KEY, apiKey);
        log.info("apiKeyObject: {}", apiKeyObject);
        if (apiKeyObject != null) {
            ApiKey key = MapperUtils.objectMapper(apiKeyObject, ApiKey.class);
            return key.getServices().contains(routeId);
        } else {
            log.info("apiKeyObject is null");
            return false;
        }
    }
}
