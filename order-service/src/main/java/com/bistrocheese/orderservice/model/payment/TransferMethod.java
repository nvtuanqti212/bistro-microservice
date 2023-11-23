package com.bistrocheese.orderservice.model.payment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transfer_method")
public class TransferMethod{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "method_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private MethodType methodType;

    @Column(name = "method_name", nullable = false)
    private String methodName;

    @Column(name = "acc_num", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "acc_holder_name", nullable = false)
    private String accountHolderName;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}
