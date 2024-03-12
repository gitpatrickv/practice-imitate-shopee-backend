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
        //List<Cart> cart = cartRepository.findAll();
        List<Cart> cart = cartRepository.findAllByFilterTrueAndUserEmailAndCreatedBy(username, username);

        for (Cart carts : cart) {
           // if (carts.getCreatedBy().equals(username) && carts.isFilter()) {
                cartModel.add(mapper.mapCartEntityToCartModel(carts));

                OrderModel orderModel = OrderModel.builder()
                        .name(carts.getUser().getName())
                        .address(carts.getUser().getAddress())
                        .email(carts.getUser().getEmail())
                        .contactNumber(carts.getUser().getContactNumber())

                        .price(carts.getPrice())
                        .totalAmount(carts.getTotalAmount())
                        .shopName(carts.getShopName())
                        .productName(carts.getProductName())
                        .quantity(carts.getQuantity())

                        .productId(carts.getProduct().getProductId())
                        .userId(carts.getUser().getEmail())

                        .paymentMethod("CASH ON DELIVERY")
                        .orderStatus("PROCESSING")
                        .build();

                this.order(orderModel);
            //}
        }

        cartRepository.deleteAllByFilterTrueAndUserEmailAndCreatedBy(username,username);
    }

    private void order(OrderModel orderModel) {

        Order order = Order.builder()
                .name(orderModel.getName())
                .address(orderModel.getAddress())
                .email(orderModel.getEmail())
                .contactNumber(orderModel.getContactNumber())

                .price(orderModel.getPrice())
                .totalAmount(orderModel.getTotalAmount())
                .shopName(orderModel.getShopName())
                .productName(orderModel.getProductName())
                .quantity(orderModel.getQuantity())

                .productId(orderModel.getProductId())
                .userId(orderModel.getUserId())

                .paymentMethod(orderModel.getPaymentMethod())
                .orderStatus(orderModel.getOrderStatus())

                .build();

        orderRepository.save(order);
    }
}
