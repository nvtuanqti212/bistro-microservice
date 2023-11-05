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
    public static final String MANAGER_ID = MANAGERS + "/{managerId}";
    public static final String SCHEDULES = MANAGER_ID + "/schedules";
    public static final String STAFF_ID = "/{staffId}";
}
