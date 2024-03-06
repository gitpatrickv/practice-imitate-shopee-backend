package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.model.CartModel;
import com.springboot.practiceimitateshopeebackend.repository.CartRepository;
import com.springboot.practiceimitateshopeebackend.repository.ProductRepository;
import com.springboot.practiceimitateshopeebackend.repository.UserRepository;
import com.springboot.practiceimitateshopeebackend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final ProductServiceImpl productService;
    @Override
    public CartModel addToCart(Integer id) {






        return null;
    }

    /*

       //get username from jwt header
       // if(username == null && SecurityContextHolder.getContext().getAuthentication() == null){
        //create cart container to hold products ???
        //get product add product to cart
        //}
    check if user is login
    add product to cart
    create a container for product
     */
}
