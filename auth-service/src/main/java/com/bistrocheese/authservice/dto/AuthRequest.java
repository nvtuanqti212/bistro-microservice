package com.bistrocheese.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Integer role;
    private Integer status;
    private String dateOfBirth;
    private String phoneNumber;
    private String foreignLanguage;
    // Staff
    private String academicLevel;
    // Manager
    private String certificationManagement;
    private Integer experiencedYear;
    // Owner
    private String branch;
    private String licenseBusiness;
}
