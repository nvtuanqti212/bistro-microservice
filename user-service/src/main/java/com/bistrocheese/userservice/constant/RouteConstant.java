package com.bistrocheese.userservice.constant;

public class RouteConstant {
    public static final String VERSION = "/v1";
    public static final String API = "/api" + VERSION;

    // Owner
    public static final String OWNER = API + "/owner";
    public static final String USERS = "/users";
    public static final String USER_ID = USERS + "/{userId}";
    public static final String SEARCH = USERS + "/search";

    // Manager
    public static final String MANAGERS = API + "/managers";
}
