package com.bistrocheese.userservice.service.user.factory;

import com.bistrocheese.userservice.constant.DateConstant;
import com.bistrocheese.userservice.constant.MessageConstant;
import com.bistrocheese.userservice.dto.request.user.UserRequest;
import com.bistrocheese.userservice.exception.BadRequestException;
import com.bistrocheese.userservice.model.user.baseUser.Role;
import com.bistrocheese.userservice.model.user.baseUser.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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
                .role(Role.convertIntToRole(userRequest.getRole()))
                .status(userRequest.getStatus())
                .password(userRequest.getPassword())
                .phoneNumber(userRequest.getPhoneNumber())
                .dateOfBirth(dob)
                .createdDate(date)
                .lastModifiedDate(date)
                .build();
        return createUser(user, userRequest);
    }

    public User update(User user, UserRequest userRequest) {
        // Update Role
        if (!Objects.equals(userRequest.getRole(), user.getRole().ordinal())) {
            String userId = user.getId();
            Date createdDate = user.getCreatedDate();
            user = this.create(userRequest);
            user.setCreatedDate(createdDate);
            user.setId(userId);
            return user;
        }

        Date date = new Date();
        String dateOfBirth = userRequest.getDateOfBirth();
        DateFormat dateFormat = new SimpleDateFormat(DateConstant.FORMAT_DATE);
        Date dob;
        try {
            dob = dateFormat.parse(dateOfBirth);
        } catch (Exception e) {
            throw new BadRequestException(MessageConstant.INVALID_DATE_OF_BIRTH);
        }
        user.setEmail(userRequest.getEmail());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setStatus(userRequest.getStatus());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setDateOfBirth(dob);
        user.setLastModifiedDate(date);
        user = updateUser(user, userRequest);
        return user;
    }

    protected abstract User createUser(User user, UserRequest userRequest);

    protected abstract User updateUser(User user, UserRequest userRequest);
}
