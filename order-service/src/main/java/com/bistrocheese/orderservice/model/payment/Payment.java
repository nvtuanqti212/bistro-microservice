package com.bistrocheese.orderservice.model.payment;

import com.bistrocheese.orderservice.model.Order;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "type", nullable = false)

    @Enumerated(EnumType.ORDINAL)
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "method_id")
    private TransferMethod methodId;

    @Column(name = "cus_nme",nullable = false)
    private String customerName;

    @Column(name = "phone_num",nullable = false)
    private String phoneNumber;
}