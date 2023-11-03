package com.bistrocheese.userservice.service.factory;

import com.bistrocheese.userservice.constant.DateConstant;
import com.bistrocheese.userservice.constant.MessageConstant;
import com.bistrocheese.userservice.dto.request.user.UserRequest;
import com.bistrocheese.userservice.exception.BadRequestException;
import com.bistrocheese.userservice.model.user.baseUser.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class UserFactory {
    public User create(UserRequest userRequest) {
        Date date = new Date();
        String dateOfBirth = userRequest.getDateOfBirth();
        DateFormat dateFormat = new SimpleDateFormat(DateConstant.FORMAT_DATE);
        Date dob;
        try {
            dob = dateFormat.parse(dateOfBirth);
        } catch (Exception e) {
            throw new BadRequestException(MessageConstant.INVALID_DATE_OF_BIRTH);
        }
        User user = User.builder()
                .email(userRequest.getEmail())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .roleId(userRequest.getRoleId())
                .status(userRequest.getStatus())
                .password(userRequest.getPassword())
                .phoneNumber(userRequest.getPhoneNumber())
                .dateOfBirth(dob)
                .createdDate(date)
                .lastModifiedDate(date)
                .build();
        user = createUser(user, userRequest);
        return user;
    }

    protected abstract User createUser(User user, UserRequest userRequest);
}
