package com.bistrocheese.foodservice.model;

import com.bistrocheese.foodservice.constant.DateTimeConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "food")
public class Food implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "description", nullable = false)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String description;

    @Column(name = "product_image", nullable = false)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String productImage;

    @Column(name = "price", nullable = false)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private BigDecimal price;


    @Column(name = "status", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Integer status;

    @CreatedDate
    @Column(name = "created_date")
    @JsonFormat(pattern = DateTimeConstant.FORMAT_DATE_TIME, timezone = DateTimeConstant.TIMEZONE)
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @JsonFormat(pattern = DateTimeConstant.FORMAT_DATE_TIME, timezone = DateTimeConstant.TIMEZONE)
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    private Date lastModifiedDate;

}
