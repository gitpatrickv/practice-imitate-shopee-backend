package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.Cart;
import com.springboot.practiceimitateshopeebackend.entity.Inventory;
import com.springboot.practiceimitateshopeebackend.entity.User;
import com.springboot.practiceimitateshopeebackend.model.CartModel;
import com.springboot.practiceimitateshopeebackend.model.CartRequest;
import com.springboot.practiceimitateshopeebackend.repository.CartRepository;
import com.springboot.practiceimitateshopeebackend.repository.InventoryRepository;
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
    private final InventoryRepository inventoryRepository;

    @Override
    public void addToCart(CartRequest cartRequest) {
        String username = JwtAuthenticationFilter.CURRENT_USER;
        Optional<User> user = userRepository.findById(username);
        Optional<Inventory> inventory = inventoryRepository.findById(cartRequest.getId());
        Optional<Cart> existingCart = cartRepository.findByInventory_InventoryIdAndUserEmail(cartRequest.getId(), username);

        if(existingCart.isPresent() && existingCart.get().getCreatedBy().equals(username)){
            Cart cart = existingCart.get();
            if(cart.getQuantity() < inventory.get().getQuantity()){
                cart.setQuantity(cart.getQuantity() + cartRequest.getQuantity());
                cart.setTotalAmount(cart.getQuantity() * inventory.get().getPrice());
                cartRepository.save(cart);
            }else{
                log.info(StringUtils.OUT_OF_STOCK);
            }
        }else{
            Cart cart = new Cart();
            if(cartRequest.getQuantity() > inventory.get().getQuantity()){
                log.info(StringUtils.OUT_OF_STOCK);
            }else{
                cart.setInventory(inventory.get());
                cart.setQuantity(cartRequest.getQuantity());
                cart.setPrice(inventory.get().getPrice());
                cart.setShopName(inventory.get().getShopName());
                cart.setProductName(inventory.get().getProductName());
                cart.setTotalAmount(inventory.get().getPrice() * cartRequest.getQuantity());
                cart.setUser(user.get());
                cartRepository.save(cart);
            }
        }
    }

    @Override
    public void increaseQuantity(Long id) {

    }

    @Override
    public void decreaseQuantity(Long id) {

    }

    @Override
    public void filterCart(Long id) {

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
    public void deleteProductsInCart(Long id) {

    }


}


