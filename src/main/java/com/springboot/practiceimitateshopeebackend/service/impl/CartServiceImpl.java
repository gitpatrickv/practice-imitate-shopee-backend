package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.model.CartModel;
import com.springboot.practiceimitateshopeebackend.model.CartRequest;
import com.springboot.practiceimitateshopeebackend.repository.CartRepository;
import com.springboot.practiceimitateshopeebackend.repository.InventoryRepository;
import com.springboot.practiceimitateshopeebackend.repository.ProductRepository;
import com.springboot.practiceimitateshopeebackend.repository.UserRepository;
import com.springboot.practiceimitateshopeebackend.security.JwtAuthenticationFilter;
import com.springboot.practiceimitateshopeebackend.service.CartService;
import com.springboot.practiceimitateshopeebackend.utils.mapper.CartMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartMapper mapper;
    private final InventoryRepository inventoryRepository;

    @Override
    public void addToCart(CartRequest cartRequest) {

    }

    @Override
    public List<CartModel> cartList() {
        String username = JwtAuthenticationFilter.CURRENT_USER;
        return cartRepository.findAll()
                .stream()
                .filter(filter -> filter.getCreatedBy().equals(username))
                .map(mapper::mapCartEntityToCartModel)
                .toList();
    }







}


