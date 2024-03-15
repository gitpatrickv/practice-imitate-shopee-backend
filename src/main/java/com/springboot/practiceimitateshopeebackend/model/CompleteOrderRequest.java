package com.springboot.practiceimitateshopeebackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompleteOrderRequest {

    private String shopName;
    private Long productId;
}
