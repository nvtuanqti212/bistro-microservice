package com.bistrocheese.userservice.constant;

public class RouteConstant {
    public static final String VERSION = "/v1";
    public static final String API = "/api" + VERSION;

    // Owner
    public static final String USERS = API + "/users";
    public static final String USER_ID = "/{userId}";

    // Create Order
    public static final String CREATE_ORDER = USER_ID + "/orders";

}
