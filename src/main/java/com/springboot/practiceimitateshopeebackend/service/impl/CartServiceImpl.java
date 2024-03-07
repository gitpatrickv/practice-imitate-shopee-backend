package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.Cart;
import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.entity.User;
import com.springboot.practiceimitateshopeebackend.model.CartModel;
import com.springboot.practiceimitateshopeebackend.repository.CartRepository;
import com.springboot.practiceimitateshopeebackend.repository.ProductRepository;
import com.springboot.practiceimitateshopeebackend.repository.UserRepository;
import com.springboot.practiceimitateshopeebackend.security.JwtAuthenticationFilter;
import com.springboot.practiceimitateshopeebackend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    @Override
    public void addToCart(CartModel cartModel) {

        String username = JwtAuthenticationFilter.CURRENT_USER;
        Optional<User> user = userRepository.findById(username);
        Optional<Product> product = productRepository.findById(cartModel.getProductId());

        if(product.isPresent()){
            Cart cart = new Cart();
            cart.setProduct(product.get());
            cart.setQuantity(cartModel.getQuantity());
            cart.setProductAmount(product.get().getProductAmount() * cartModel.getQuantity());
            cart.setShopName(product.get().getShopName());
            cart.setProductName(product.get().getProductName());
            cart.setUser(user.get());

            cartRepository.save(cart);
        }

    }
    //todo check if user is logged in
    //todo check if the product already exists in the cart
    //todo quantity + -
    //todo checkout
    //todo order details
}
