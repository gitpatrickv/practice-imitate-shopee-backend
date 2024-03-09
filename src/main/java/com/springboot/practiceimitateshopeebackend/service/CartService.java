package com.springboot.practiceimitateshopeebackend.service;

import com.springboot.practiceimitateshopeebackend.entity.Cart;
import com.springboot.practiceimitateshopeebackend.model.CartModel;
import com.springboot.practiceimitateshopeebackend.model.CartRequest;
import com.springboot.practiceimitateshopeebackend.model.Response;

import java.util.List;

public interface CartService {

    void addToCart(CartRequest cartRequest);
    List<CartModel> cartList();
    public List<CartModel> checkout();
    void increaseQuantity(Long id);
    void decreaseQuantity(Long id);
    void filterCart(Long id);
    void delete(Long id);

}
