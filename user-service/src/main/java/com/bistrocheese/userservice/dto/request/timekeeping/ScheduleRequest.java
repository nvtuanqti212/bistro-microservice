package com.bistrocheese.userservice.dto.request.timekeeping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequest {
    private List<String> staffIds;
    private DayOfWeek dayOfWeek;
    private Integer shift;
}
