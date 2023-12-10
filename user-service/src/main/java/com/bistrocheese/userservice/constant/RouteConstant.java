package com.bistrocheese.userservice.constant;

public class RouteConstant {
    public static final String SERVICE = "/user-service";
    public static final String API = SERVICE + "/api";

    // Owner
    public static final String USERS = API + "/users";
    public static final String USER_ID = "/{userId}";

    // Create Order
    public static final String CREATE_ORDER = "/orders";

}
