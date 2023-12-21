package com.bistrocheese.userservice.constant;

public class RouteConstant {
    public static final String SERVICE = "/user-service";
    public static final String API = SERVICE + "/api";
    public static final String ID = "/{id}";

    // Owner
    public static final String USERS = API + "/users";
    public static final String USER_ID = "/{userId}";

    // Order
    public static final String ORDER = "/orders";
    public static final String ORDER_ID = ORDER + ID;

    // Payment
    public static final String PAYMENT = "/payments";

}
