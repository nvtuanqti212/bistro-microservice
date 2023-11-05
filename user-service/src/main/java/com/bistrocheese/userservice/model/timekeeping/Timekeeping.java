package com.bistrocheese.userservice.model.timekeeping;

import com.bistrocheese.userservice.constant.DateConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Timekeeping implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String managerId;
    private String staffId;
    private Integer scheduleId;
    @JsonFormat(pattern = DateConstant.FORMAT_DATE_TIME, timezone = DateConstant.TIMEZONE)
    private LocalDateTime workDate;
    private Integer status;
}
