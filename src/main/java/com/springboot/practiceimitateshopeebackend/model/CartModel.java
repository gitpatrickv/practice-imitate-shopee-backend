package com.springboot.practiceimitateshopeebackend.model;

import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartModel {

    private ProductModel productModel;
    private UserModel userModel;
    private Double total;
}
