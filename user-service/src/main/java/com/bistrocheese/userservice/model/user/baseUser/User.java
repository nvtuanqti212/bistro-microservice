package com.bistrocheese.userservice.model.user.baseUser;

import com.bistrocheese.userservice.constant.DateConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private Integer status;
    private String password;
    private String phoneNumber;

    @JsonFormat(pattern = DateConstant.FORMAT_DATE, timezone = DateConstant.TIMEZONE)
    private Date dateOfBirth;

    @CreatedDate
    @JsonFormat(pattern = DateConstant.FORMAT_DATE_TIME, timezone = DateConstant.TIMEZONE)
    private Date createdDate;

    @LastModifiedDate
    @JsonFormat(pattern = DateConstant.FORMAT_DATE_TIME, timezone = DateConstant.TIMEZONE)
    private Date lastModifiedDate;

    public User(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = user.getRole();
        this.status = user.getStatus();
        this.password = user.getPassword();
        this.phoneNumber = user.getPhoneNumber();
        this.dateOfBirth = user.getDateOfBirth();
        this.createdDate = user.getCreatedDate();
        this.lastModifiedDate = user.getLastModifiedDate();
    }
}
