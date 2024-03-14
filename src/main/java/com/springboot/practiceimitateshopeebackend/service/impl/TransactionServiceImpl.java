package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.Order;
import com.springboot.practiceimitateshopeebackend.entity.Transaction;
import com.springboot.practiceimitateshopeebackend.repository.TransactionRepository;
import com.springboot.practiceimitateshopeebackend.service.TransactionService;
import com.springboot.practiceimitateshopeebackend.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;


        protected void saveCancelledTransaction(Order order){

        Transaction transaction = new Transaction();
        transaction.setProductName(order.getProductName());
        transaction.setShopName(order.getShopName());
        transaction.setPrice(order.getPrice());
        transaction.setTotalAmount(order.getTotalAmount());
        transaction.setQuantity(order.getQuantity());
        transaction.setOrderStatus(StringUtils.ORDER_CANCELLED);
        transaction.setCreatedBy(order.getCreatedBy());

        transactionRepository.save(transaction);
    }


}
