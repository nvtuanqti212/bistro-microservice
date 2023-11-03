package com.bistrocheese.userservice.model.timekeeping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.DayOfWeek;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private DayOfWeek day;
    private Integer shift;
}
