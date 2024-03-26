package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.Cart;
import com.springboot.practiceimitateshopeebackend.entity.Inventory;
import com.springboot.practiceimitateshopeebackend.entity.Order;
import com.springboot.practiceimitateshopeebackend.model.CartModel;
import com.springboot.practiceimitateshopeebackend.repository.CartRepository;
import com.springboot.practiceimitateshopeebackend.repository.InventoryRepository;
import com.springboot.practiceimitateshopeebackend.repository.OrderRepository;
import com.springboot.practiceimitateshopeebackend.security.JwtAuthenticationFilter;
import com.springboot.practiceimitateshopeebackend.service.OrderService;
import com.springboot.practiceimitateshopeebackend.utils.StringUtils;
import com.springboot.practiceimitateshopeebackend.utils.mapper.CartMapper;
import com.springboot.practiceimitateshopeebackend.utils.mapper.OrderMapper;
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
public class OrderServiceImpl implements OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final CartMapper mapper;
    private final InventoryRepository inventoryRepository;
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

        for(Cart carts : cart){
            this.orderDetails(carts);
            this.updateQuantity(carts);
        }
        cartRepository.deleteAllByFilterTrueAndUserEmail(username);
    }


    @Override
    public void cancelOrder(String shopName) {
        String username = JwtAuthenticationFilter.CURRENT_USER;

        List<Order> order = orderRepository.findAllByEmailAndShopName(username, shopName);

        for(Order orders : order){
            transactionService.saveCancelledOrder(orders);
            this.updateQuantityAfterOrderCancellation(orders);
        }
        orderRepository.deleteAllByEmailAndShopName(username, shopName);
    }

    @Override
    public void completeOrder(String shopName) {
        String username = JwtAuthenticationFilter.CURRENT_USER;
        List<Order> order = orderRepository.findAllByEmailAndShopName(username, shopName);

        for(Order orders : order){
            transactionService.saveCompletedOrder(orders);
        }
        orderRepository.deleteAllByEmailAndShopName(username, shopName);
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
        order.setColor(cart.getColor());
        order.setSize(cart.getSize());
        order.setCreatedBy(cart.getCreatedBy());
        order.setPaymentMethod(StringUtils.CASH_ON_DELIVERY);
        order.setOrderStatus(StringUtils.PROCESSING);
        order.setUser(cart.getUser());
        order.setInventoryId(cart.getInventory().getInventoryId());
        orderRepository.save(order);
    }

    private void updateQuantity(Cart cart){
        Optional<Inventory> inventory = inventoryRepository.findById(cart.getInventory().getInventoryId());

        if(cart.getQuantity() > inventory.get().getQuantity()){
            throw new IllegalArgumentException(StringUtils.OUT_OF_STOCK);
        }else{
            inventory.get().setQuantity(inventory.get().getQuantity() - cart.getQuantity());
        }
    }

    private void updateQuantityAfterOrderCancellation(Order order){
        Optional<Inventory> inventory = inventoryRepository.findById(order.getInventoryId());
        inventory.get().setQuantity(inventory.get().getQuantity() + order.getQuantity());
    }
}