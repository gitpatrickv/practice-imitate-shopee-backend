package com.springboot.practiceimitateshopeebackend.utils.mapper;

import com.springboot.practiceimitateshopeebackend.entity.Order;
import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.model.OrderModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderMapper {

    private final ModelMapper mapper = new ModelMapper();
    public OrderModel mapOrderEntityToOrderModel(Order order){
        return mapper.map(order, OrderModel.class);
    }
    public Order mapOrderModelToOrderEntity(OrderModel orderModel){
        return mapper.map(orderModel, Order.class);
    }
}
