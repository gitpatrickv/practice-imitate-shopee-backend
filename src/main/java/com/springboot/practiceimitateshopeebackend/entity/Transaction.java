package com.springboot.practiceimitateshopeebackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class Transaction extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_gen")
    @SequenceGenerator(name = "transaction_gen", sequenceName = "transaction_seq", allocationSize = 1)
    private Long transactionId;
    private String productName;
    private String shopName;
    private Double price;
    private Double totalAmount;
    private Long quantity;
    private String orderStatus;
    private Long inventoryId;
    private String paymentMethod;
    private String size;
    private String color;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
