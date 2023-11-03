package com.bistrocheese.userservice.constant;

import lombok.Getter;

@Getter
public class MessageConstant {
    // Success messages
    public static final String CREATE_USER_SUCCESS = "Created user successfully";
    public static final String GET_USERS_SUCCESS = "Get users successfully";
    public static final String GET_USER_SUCCESS = "Get user successfully";
    public static final String UPDATE_USER_SUCCESS = "Updated user successfully";
    public static final String DELETE_USER_SUCCESS = "Deleted user successfully";

    // Exception messages
    public static final String USER_NOT_FOUND = "User not found";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
    public static final String EMAIL_NOT_FOUND = "Email not found";
    public static final String INVALID_ROLE_ID = "Invalid role id";
    public static final String INVALID_DATE_OF_BIRTH = "Invalid date of birth";
}
