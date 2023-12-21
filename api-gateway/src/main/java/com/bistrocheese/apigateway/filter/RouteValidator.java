package com.bistrocheese.apigateway.filter;

import lombok.NoArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
@NoArgsConstructor
public class RouteValidator {
    public static final List<String> openRoutes = List.of(
            "/auth-service/api/auth/register",
            "/auth-service/api/auth/login",
            "/eureka/**"
    );

    public Predicate<ServerHttpRequest> isSecured = request -> openRoutes
            .stream()
            .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
