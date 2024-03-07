package com.springboot.practiceimitateshopeebackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartModel {
    private Long quantity;
    private Double price;
    private Double totalAmount;
    private String shopName;
    private String productName;

}
