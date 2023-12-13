package com.bistrocheese.paymentservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
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