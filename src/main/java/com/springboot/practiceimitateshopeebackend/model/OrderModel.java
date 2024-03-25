package com.springboot.practiceimitateshopeebackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderModel {

    private Long orderId;
    private String name;
    private String address;
    private String email;
    private String contactNumber;
    private Double price;
    private Double totalAmount;
    private String shopName;
    private String productName;
    private Long quantity;
    private Long productId;
    private String userId;
    private String paymentMethod;
    private String orderStatus;

}
