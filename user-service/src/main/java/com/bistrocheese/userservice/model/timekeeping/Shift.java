package com.bistrocheese.userservice.model.timekeeping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Shift {
    MORNING(7,12),
    AFTERNOON(12, 17),
    NIGHT(17, 22);

    private final int start;
    private final int end;
}
