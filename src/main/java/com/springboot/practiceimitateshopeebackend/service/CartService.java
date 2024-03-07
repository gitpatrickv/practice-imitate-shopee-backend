package com.springboot.practiceimitateshopeebackend.service;

import com.springboot.practiceimitateshopeebackend.entity.Cart;
import com.springboot.practiceimitateshopeebackend.model.CartModel;

import java.util.List;

public interface CartService {

    void addToCart(CartModel cartModel);

    //List<Cart> getOne(Long id);

    //getCartByUser
}
