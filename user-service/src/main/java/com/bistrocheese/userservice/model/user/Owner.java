package com.bistrocheese.userservice.model.user;

import com.bistrocheese.userservice.model.user.baseUser.User;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "owners")
public class Owner extends User {
    private String branch;
    private String licenseBusiness;

    public Owner (
            User user,
            String branch,
            String licenseBusiness
    ) {
        super(user);
        this.branch = branch;
        this.licenseBusiness = licenseBusiness;
    }
}
