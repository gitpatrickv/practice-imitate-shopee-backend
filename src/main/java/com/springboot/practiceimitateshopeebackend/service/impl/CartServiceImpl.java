package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.Cart;
import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.entity.User;
import com.springboot.practiceimitateshopeebackend.model.CartModel;
import com.springboot.practiceimitateshopeebackend.model.CartRequest;
import com.springboot.practiceimitateshopeebackend.repository.CartRepository;
import com.springboot.practiceimitateshopeebackend.repository.ProductRepository;
import com.springboot.practiceimitateshopeebackend.repository.UserRepository;
import com.springboot.practiceimitateshopeebackend.security.JwtAuthenticationFilter;
import com.springboot.practiceimitateshopeebackend.service.CartService;
import com.springboot.practiceimitateshopeebackend.utils.StringUtils;
import com.springboot.practiceimitateshopeebackend.utils.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartMapper mapper;
    @Override
    public void addToCart(CartRequest cartRequest) {

        String username = JwtAuthenticationFilter.CURRENT_USER;
        Optional<User> user = userRepository.findById(username);
        Optional<Product> product = productRepository.findById(cartRequest.getProductId());
        Optional<Cart> existingCart = cartRepository.findByProduct_ProductId(cartRequest.getProductId());

        try {
            if (existingCart.isPresent()) {
                Cart cart = existingCart.get();
                cart.setQuantity(existingCart.get().getQuantity() + cartRequest.getQuantity());
                cart.setProductAmount(existingCart.get().getQuantity() * product.get().getProductAmount());
                cartRepository.save(cart);
            } else {
                Cart cart = new Cart();
                cart.setProduct(product.get());
                cart.setQuantity(cartRequest.getQuantity());
                cart.setProductAmount(product.get().getProductAmount() * cartRequest.getQuantity());
                cart.setShopName(product.get().getShopName());
                cart.setProductName(product.get().getProductName());
                cart.setUser(user.get());
                cartRepository.save(cart);
            }
        }
        catch (Exception e){
            throw new NoSuchElementException(StringUtils.OUT_OF_STOCK);
        }
    }

    @Override
    public List<CartModel> viewMyCart(String email) {
        return cartRepository.findAll()
                .stream()
                //.filter(cart -> cart.getUser().equals(email))
                .map(mapper::mapCartEntityToCartModel)
                .toList();
    }


    //todo check if user is logged in
    //todo check if the product already exists in the cart
    //todo quantity + -
    //todo checkout
    //todo order details
}
