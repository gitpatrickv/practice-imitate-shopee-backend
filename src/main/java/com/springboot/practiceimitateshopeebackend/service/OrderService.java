package com.springboot.practiceimitateshopeebackend.service;

import com.springboot.practiceimitateshopeebackend.model.CartModel;

import java.util.List;

public interface OrderService {

    void placeOrder();
    List<CartModel> checkout();
    void cancelOrder(String shopName);
    void completeOrder(String shopName);


}
