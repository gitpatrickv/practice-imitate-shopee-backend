package com.springboot.practiceimitateshopeebackend.service;

import com.springboot.practiceimitateshopeebackend.model.CartModel;

public interface CartService {

    CartModel addToCart(Integer id);
}
