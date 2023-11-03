package com.bistrocheese.userservice.model.user;

import com.bistrocheese.userservice.model.user.baseUser.User;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "managers")
public class Manager extends User {
    private String certificationManagement;
    private String foreignLanguage;
    private Integer experiencedYear;

    public Manager (
            User user,
            String certificationManagement,
            String foreignLanguage,
            Integer experiencedYear
    ) {
        super(user);
        this.certificationManagement = certificationManagement;
        this.foreignLanguage = foreignLanguage;
        this.experiencedYear = experiencedYear;
    }
}