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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    @Override
    public void addToCart(Long id) {

        String username = JwtAuthenticationFilter.CURRENT_USER;
        Optional<User> user = userRepository.findById(username);
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            Cart cart = new Cart();
            cart.setProduct(product.get());
            cart.setProductAmount(product.get().getProductAmount());
            cart.setQuantity(1L);
            cart.setUser(user.get());

            cartRepository.save(cart);
        }

    }

    /*


    check if user is login
    add product to cart
    create a container for product
     */
}
