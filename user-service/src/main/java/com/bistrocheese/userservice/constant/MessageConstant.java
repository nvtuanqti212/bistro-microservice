package com.bistrocheese.userservice.constant;

import lombok.Getter;

@Getter
public class MessageConstant {
    // Success messages
        // Users
    public static final String CREATE_USER_SUCCESS = "Created user successfully";
    public static final String GET_USERS_SUCCESS = "Get users successfully";
    public static final String GET_USER_DETAIL_SUCCESS = "Get user detail successfully";
    public static final String UPDATE_USER_SUCCESS = "Updated user successfully";
    public static final String DELETE_USER_SUCCESS = "Deleted user successfully";
    public static final String SEARCH_USER_SUCCESS = "Searched user successfully";

    // Exception messages
    public static final String USER_NOT_FOUND = "User not found";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
    public static final String EMAIL_NOT_FOUND = "Email not found";
    public static final String INVALID_ROLE_ID = "Invalid role id";
    public static final String INVALID_DATE_OF_BIRTH = "Invalid date of birth";
    public static final String INVALID_PAGE_NUMBER = "Page number must be greater than 0";
    public static final String INVALID_PAGE_SIZE = "Page size must be greater than 0";

    //Order
    public static final String CREATE_ORDER_SUCCESS = "Created order successfully";
    public static final String ORDER_NOT_FOUND = "Get order successfully";

    //circuit breaker

}
