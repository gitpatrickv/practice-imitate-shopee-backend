package com.springboot.practiceimitateshopeebackend.controller;

import com.springboot.practiceimitateshopeebackend.entity.Cart;
import com.springboot.practiceimitateshopeebackend.model.CartModel;
import com.springboot.practiceimitateshopeebackend.model.CartRequest;
import com.springboot.practiceimitateshopeebackend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CartModel> cartList(String email){
        return cartService.cartList(email);
    }
    @GetMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void addToCart(@RequestBody CartRequest cartRequest){
        cartService.addToCart(cartRequest);
    }



}
