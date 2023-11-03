package com.bistrocheese.userservice.model.user;

import com.bistrocheese.userservice.model.user.baseUser.User;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "staffs")
public class Staff extends User {
    private String foreignLanguage;
    private String academicLevel;

    public Staff (
            User user,
            String foreignLanguage,
            String academicLevel
    ) {
        super(user);
        this.foreignLanguage = foreignLanguage;
        this.academicLevel = academicLevel;
    }
}
