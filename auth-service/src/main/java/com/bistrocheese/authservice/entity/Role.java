package com.bistrocheese.authservice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    CUSTOMER, SELLER, ADMIN
}
