package com.springboot.practiceimitateshopeebackend.service;

import com.springboot.practiceimitateshopeebackend.entity.Cart;
import com.springboot.practiceimitateshopeebackend.model.CartModel;

public interface CartService {

    void addToCart(CartModel cartModel);

    //getCartByUser
}
