package com.springboot.practiceimitateshopeebackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionModel {

    private String transactionId;
    private String productName;
    private String shopName;
    private Double price;
    private Double totalAmount;
    private Long quantity;
    private String orderStatus;
}
