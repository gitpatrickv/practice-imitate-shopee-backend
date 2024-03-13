package com.springboot.practiceimitateshopeebackend.service;

import com.springboot.practiceimitateshopeebackend.entity.Order;
import com.springboot.practiceimitateshopeebackend.model.CartModel;
import com.springboot.practiceimitateshopeebackend.model.OrderModel;

import java.util.List;

public interface OrderService {

    void placeOrder();
    List<CartModel> checkout();
    void cancelOrder();


}
