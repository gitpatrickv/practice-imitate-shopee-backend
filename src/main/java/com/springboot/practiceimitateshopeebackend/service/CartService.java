package com.springboot.practiceimitateshopeebackend.service;

import com.springboot.practiceimitateshopeebackend.model.CartModel;
import com.springboot.practiceimitateshopeebackend.model.CartRequest;

import java.util.List;

public interface CartService {

    void addToCart(CartRequest cartRequest);
    void increaseQuantity(Long id);
    void decreaseQuantity(Long id);
    void filterCart(Long id);
    List<CartModel> cartList();
    void deleteOneProductInCart(Long id);
    void deleteAllProductsInCart();

}
