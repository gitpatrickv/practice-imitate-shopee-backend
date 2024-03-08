package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.Cart;
import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.entity.User;
import com.springboot.practiceimitateshopeebackend.model.CartModel;
import com.springboot.practiceimitateshopeebackend.model.CartRequest;
import com.springboot.practiceimitateshopeebackend.model.Response;
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
        Optional<User> user = userRepository.findById(cartRequest.getEmail());
        Optional<Product> product = productRepository.findById(cartRequest.getProductId());
        Optional<Cart> existingCart = cartRepository.findByProduct_ProductIdAndUserEmail(cartRequest.getProductId(), cartRequest.getEmail());

        if (existingCart.isPresent() && existingCart.get().getCreatedBy().equals(username)){
            Cart cart = (Cart) existingCart.get();
            cart.setQuantity(existingCart.get().getQuantity() + cartRequest.getQuantity());
            cart.setTotalAmount(existingCart.get().getQuantity() * product.get().getPrice());
            cart.setLastModifiedBy(user.get().getEmail());
            cartRepository.save(cart);
        }
        else {
            Cart cart = new Cart();
            cart.setProduct(product.get());
            cart.setQuantity(cartRequest.getQuantity());        //todo: check quantity < request quantity
            cart.setPrice(product.get().getPrice());
            cart.setShopName(product.get().getShopName());
            cart.setProductName(product.get().getProductName());
            cart.setTotalAmount(product.get().getPrice() * cartRequest.getQuantity());
            cart.setUser(user.get());
            cart.setCreatedBy(user.get().getEmail());
            cartRepository.save(cart);
        }
    }

    @Override
    public List<CartModel> cartList(String email) {
        return cartRepository.findAll()
                .stream()
                .map(mapper::mapCartEntityToCartModel)
                .toList();
    }

    @Override
    public void increaseQuantity(Long id) {     //todo: set max limit of increase quantity based on products quantity

        String username = JwtAuthenticationFilter.CURRENT_USER;
        Optional<Product> product = productRepository.findById(id);
        Optional<Cart> existingCart = cartRepository.findByProduct_ProductIdAndUserEmail(id, username);

        if(existingCart.isPresent()  && existingCart.get().getCreatedBy().equals(username)) {
            Cart cart = existingCart.get();

            if(cart.getQuantity().compareTo(product.get().getQuantity()) >= 0){
                throw new IllegalArgumentException(StringUtils.OUT_OF_STOCK);
                //if(existingCart.get().getQuantity().compareTo(product.get().getQuantity()) > 0)){
            }else {
                cart.setQuantity(existingCart.get().getQuantity() + 1);
                cart.setTotalAmount(existingCart.get().getQuantity() * product.get().getPrice());
                cartRepository.save(cart);
            }
        }

    }

    @Override
    public void decreaseQuantity(Long id) {

        String username = JwtAuthenticationFilter.CURRENT_USER;
        Optional<Product> product = productRepository.findById(id);
        Optional<Cart> existingCart = cartRepository.findByProduct_ProductIdAndUserEmail(id, username);

        if(existingCart.isPresent()  && existingCart.get().getCreatedBy().equals(username)) {
            Cart cart = existingCart.get();

            if(cart.getQuantity() > 1){
                cart.setQuantity(existingCart.get().getQuantity() - 1);
                cart.setTotalAmount(existingCart.get().getQuantity() * product.get().getPrice());
                cartRepository.save(cart);

            }
        }
    }
}


