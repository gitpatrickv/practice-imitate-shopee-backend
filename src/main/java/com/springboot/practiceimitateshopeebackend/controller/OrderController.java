package com.springboot.practiceimitateshopeebackend.controller;

import com.springboot.practiceimitateshopeebackend.model.CartModel;
import com.springboot.practiceimitateshopeebackend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void placeOrder(){
        orderService.placeOrder();
    }

    @GetMapping("/checkout")
    @ResponseStatus(HttpStatus.OK)
    public List<CartModel> checkout(){
        return orderService.checkout();
    }
    @GetMapping("/cancel/{store}")
    @ResponseStatus(HttpStatus.OK)
    public void cancelOrder(@PathVariable("store") String shopName){
        orderService.cancelOrder(shopName);
    }
    @GetMapping("/complete/{store}")
    @ResponseStatus(HttpStatus.OK)
    public void completeOrder(@PathVariable("store")String shopName){
        orderService.completeOrder(shopName);
    }



}


