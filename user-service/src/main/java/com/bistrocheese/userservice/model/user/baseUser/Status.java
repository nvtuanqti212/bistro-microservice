package com.bistrocheese.userservice.model.user.baseUser;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    ACTIVE,
    INACTIVE,
}
