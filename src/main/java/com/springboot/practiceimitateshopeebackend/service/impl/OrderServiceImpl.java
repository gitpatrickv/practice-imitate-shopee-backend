package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.Cart;
import com.springboot.practiceimitateshopeebackend.entity.Order;
import com.springboot.practiceimitateshopeebackend.model.CartModel;
import com.springboot.practiceimitateshopeebackend.model.OrderModel;
import com.springboot.practiceimitateshopeebackend.repository.CartRepository;
import com.springboot.practiceimitateshopeebackend.repository.OrderRepository;
import com.springboot.practiceimitateshopeebackend.repository.ProductRepository;
import com.springboot.practiceimitateshopeebackend.repository.UserRepository;
import com.springboot.practiceimitateshopeebackend.security.JwtAuthenticationFilter;
import com.springboot.practiceimitateshopeebackend.service.OrderService;
import com.springboot.practiceimitateshopeebackend.utils.StringUtils;
import com.springboot.practiceimitateshopeebackend.utils.mapper.CartMapper;
import com.springboot.practiceimitateshopeebackend.utils.mapper.OrderMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final CartMapper mapper;

    @Override
    public void placeOrder() {
        String username = JwtAuthenticationFilter.CURRENT_USER;

        List<CartModel> cartModel = new ArrayList<>();
        List<Cart> cart = cartRepository.findAllByFilterTrueAndUserEmailAndCreatedBy(username, username);

        for (Cart carts : cart) {
            cartModel.add(mapper.mapCartEntityToCartModel(carts));
            this.orderDetails(carts);
        }

        cartRepository.deleteAllByFilterTrueAndUserEmailAndCreatedBy(username, username);
    }

    private void orderDetails(Cart cart){

        Order order = new Order();
        order.setName(cart.getUser().getName());
        order.setAddress(cart.getUser().getAddress());
        order.setEmail(cart.getUser().getEmail());
        order.setContactNumber(cart.getUser().getContactNumber());
        order.setPrice(cart.getPrice());
        order.setTotalAmount(cart.getTotalAmount());
        order.setShopName(cart.getShopName());
        order.setProductName(cart.getProductName());
        order.setQuantity(cart.getQuantity());
        order.setProductId(cart.getProduct().getProductId());
        order.setCreatedBy(cart.getCreatedBy());
        order.setPaymentMethod(StringUtils.CASH_ON_DELIVERY);
        order.setOrderStatus(StringUtils.PROCESSING);
        order.setCreatedBy(cart.getCreatedBy());

        orderRepository.save(order);

    }

}
