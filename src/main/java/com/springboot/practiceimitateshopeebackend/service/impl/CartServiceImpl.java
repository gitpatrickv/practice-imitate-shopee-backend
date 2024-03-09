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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
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
            Cart cart = existingCart.get();
            if(cart.getQuantity() < product.get().getQuantity()) {
                cart.setQuantity(existingCart.get().getQuantity() + cartRequest.getQuantity());
                cart.setTotalAmount(existingCart.get().getQuantity() * product.get().getPrice());
                cart.setLastModifiedBy(user.get().getEmail());
                cartRepository.save(cart);
            }else{
                log.info(StringUtils.OUT_OF_STOCK);
            }
        }
        else {
            Cart cart = new Cart();
            if(cartRequest.getQuantity() > product.get().getQuantity()) {
               log.info(StringUtils.OUT_OF_STOCK);
            }else{
                cart.setProduct(product.get());
                cart.setQuantity(cartRequest.getQuantity());
                cart.setPrice(product.get().getPrice());
                cart.setShopName(product.get().getShopName());
                cart.setProductName(product.get().getProductName());
                cart.setTotalAmount(product.get().getPrice() * cartRequest.getQuantity());
                cart.setUser(user.get());
                cart.setCreatedBy(user.get().getEmail());
                cartRepository.save(cart);
            }
        }
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

    @Override
    public void increaseQuantity(Long id) {

        String username = JwtAuthenticationFilter.CURRENT_USER;
        Optional<User> user = userRepository.findById(username);
        Optional<Product> product = productRepository.findById(id);
        Optional<Cart> existingCart = cartRepository.findByProduct_ProductIdAndUserEmail(id, username);

        if(existingCart.isPresent()  && existingCart.get().getCreatedBy().equals(username)) {
            Cart cart = existingCart.get();

            if(cart.getQuantity() < product.get().getQuantity()){
                cart.setQuantity(existingCart.get().getQuantity() + 1);
                cart.setTotalAmount(existingCart.get().getQuantity() * product.get().getPrice());
                cart.setLastModifiedBy(user.get().getEmail());
                cartRepository.save(cart);
            }
            else{
                log.info(StringUtils.OUT_OF_STOCK);
            }
        }
    }

    @Override
    public void decreaseQuantity(Long id) {

        String username = JwtAuthenticationFilter.CURRENT_USER;
        Optional<User> user = userRepository.findById(username);
        Optional<Product> product = productRepository.findById(id);
        Optional<Cart> existingCart = cartRepository.findByProduct_ProductIdAndUserEmail(id, username);

        if(existingCart.isPresent()  && existingCart.get().getCreatedBy().equals(username)) {
            Cart cart = existingCart.get();

            if(cart.getQuantity() > 1){
                cart.setQuantity(existingCart.get().getQuantity() - 1);
                cart.setTotalAmount(existingCart.get().getQuantity() * product.get().getPrice());
                cart.setLastModifiedBy(user.get().getEmail());
                cartRepository.save(cart);
            }else{
                cartRepository.deleteByProduct_ProductIdAndUserEmail(id, username);
            }
        }
    }

    @Override
    public void filterCart(Long id) {
        String username = JwtAuthenticationFilter.CURRENT_USER;
        Optional<User> user = userRepository.findById(username);
        Optional<Cart> existingCart = cartRepository.findByProduct_ProductIdAndUserEmail(id, username);
        Cart cart = existingCart.get();

        if(existingCart.isPresent() && existingCart.get().getCreatedBy().equals(username)) {
            if(!cart.isFilter()) {
                cart.setFilter(true);
                cart.setLastModifiedBy(user.get().getEmail());
            }else{
                cart.setFilter(false);
                cart.setLastModifiedBy(user.get().getEmail());
            }
        }
    }

    @Override
    public void delete(Long id) {
        String username = JwtAuthenticationFilter.CURRENT_USER;
        cartRepository.deleteByProduct_ProductIdAndUserEmail(id, username);
    }


}


