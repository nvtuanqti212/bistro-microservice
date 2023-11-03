package com.bistrocheese.orderservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@Table(name = "order")
public class Order {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private UUID staffId;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private OrderTable orderTable;

    @Column(name = "order_date", nullable = false)
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    private Date orderDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private OrderStatus status;
}
