package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.Cart;
import com.springboot.practiceimitateshopeebackend.entity.Order;
import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.model.CartModel;
import com.springboot.practiceimitateshopeebackend.model.OrderModel;
import com.springboot.practiceimitateshopeebackend.repository.CartRepository;
import com.springboot.practiceimitateshopeebackend.repository.OrderRepository;
import com.springboot.practiceimitateshopeebackend.repository.ProductRepository;
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
    private final ProductRepository productRepository;
    private final CartMapper mapper;
    private final OrderMapper orderMapper;
    private final TransactionServiceImpl transactionService;

    @Override
    public List<CartModel> checkout() {
        String username = JwtAuthenticationFilter.CURRENT_USER;
        return cartRepository.findAll()
                .stream()
                .filter(filter -> filter.getCreatedBy().equals(username) && filter.isFilter())
                .map(mapper::mapCartEntityToCartModel)
                .toList();
    }

    @Override
    public void placeOrder() {
        String username = JwtAuthenticationFilter.CURRENT_USER;

        List<Cart> cart = cartRepository.findAllByFilterTrueAndUserEmail(username);

        for (Cart carts : cart) {
            this.orderDetails(carts);
            this.updateQuantity(carts);
        }

        cartRepository.deleteAllByFilterTrueAndUserEmail(username);
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

    private void updateQuantity(Cart cart){
        Optional<Product> product = productRepository.findById(cart.getProduct().getProductId());
        if(cart.getQuantity() > product.get().getInventory().getQuantity()) {
            throw new IllegalArgumentException(StringUtils.OUT_OF_STOCK);
        }else{
            product.get().getInventory().setQuantity(product.get().getInventory().getQuantity() - cart.getQuantity());
        }
    }

    @Override
    public void cancelOrder(String shopName) {
        String username = JwtAuthenticationFilter.CURRENT_USER;

        List<Order> order = orderRepository.findAllByEmailAndShopName(username, shopName);

        for (Order orders : order) {
            transactionService.saveCancelledOrder(orders);
            this.updateQuantityAfterCancel(orders);
        }
        orderRepository.deleteAllByEmailAndShopName(username, shopName);
    }

    private void updateQuantityAfterCancel(Order order){
        Optional<Product> product = productRepository.findById(order.getProductId());
        product.get().getInventory().setQuantity(product.get().getInventory().getQuantity() + order.getQuantity());
    }

    @Override
    public void completeOrder(String shopName) {
        String username = JwtAuthenticationFilter.CURRENT_USER;

        List<Order> order = orderRepository.findAllByEmailAndShopName(username, shopName);

        for (Order orders : order) {
            transactionService.saveCompletedOrder(orders);
        }

        orderRepository.deleteAllByEmailAndShopName(username, shopName);
    }

}
