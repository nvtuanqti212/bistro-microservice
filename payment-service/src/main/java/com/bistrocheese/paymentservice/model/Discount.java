package com.bistrocheese.paymentservice.model;

import com.bistrocheese.paymentservice.constant.DateTimeConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "discount")
public class Discount {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DiscountType type;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Column(name = "start_date", nullable = false)
    @JsonFormat(pattern = DateTimeConstant.FORMAT_DATE_TIME, timezone = DateTimeConstant.TIMEZONE)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    @JsonFormat(pattern = DateTimeConstant.FORMAT_DATE_TIME, timezone = DateTimeConstant.TIMEZONE)
    private Date endDate;

    @Column(name = "uses_cnt", nullable = false)
    private Integer usesCount;

    @Column(name = "uses_max", nullable = false)
    private Integer usesMax;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}