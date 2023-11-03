package com.bistrocheese.userservice.model.timekeeping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    ON_TIME,
    LATE,
    ABSENT,
}
