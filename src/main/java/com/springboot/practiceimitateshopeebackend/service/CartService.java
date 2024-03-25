package com.springboot.practiceimitateshopeebackend.service;

import com.springboot.practiceimitateshopeebackend.model.CartModel;
import com.springboot.practiceimitateshopeebackend.model.CartRequest;

import java.util.List;

public interface CartService {

    void addToCart(CartRequest cartRequest);
    List<CartModel> cartList();

}
