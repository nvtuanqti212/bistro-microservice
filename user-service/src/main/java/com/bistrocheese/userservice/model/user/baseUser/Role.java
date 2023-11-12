package com.bistrocheese.userservice.model.user.baseUser;

import lombok.Getter;

@Getter
public enum Role {
    OWNER,
    MANAGER,
    STAFF;

    public static Role convertIntToRole(Integer roleId) {
        return switch (roleId) {
            case 0 -> OWNER;
            case 1 -> MANAGER;
            case 2 -> STAFF;
            default -> null;
        };
    }
}
