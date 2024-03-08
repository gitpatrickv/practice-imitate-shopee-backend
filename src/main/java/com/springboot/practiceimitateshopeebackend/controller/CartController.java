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
    @GetMapping("/addCart")
    @ResponseStatus(HttpStatus.OK)
    public void addToCart(@RequestBody CartRequest cartRequest){
        cartService.addToCart(cartRequest);
    }

    @PutMapping("increase/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void increaseQuantity(@PathVariable Long id) {
        cartService.increaseQuantity(id);
    }

    @PutMapping("decrease/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void decreaseQuantity(@PathVariable Long id) {
        cartService.decreaseQuantity(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        cartService.delete(id);
    }

}
