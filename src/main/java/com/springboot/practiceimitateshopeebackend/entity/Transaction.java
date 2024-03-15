package com.springboot.practiceimitateshopeebackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class Transaction extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transactionId;
    private String productName;
    private String shopName;
    private Double price;
    private Double totalAmount;
    private Long quantity;
    private String orderStatus;
    private Long productId;
}
