package com.springboot.practiceimitateshopeebackend.service;

import com.springboot.practiceimitateshopeebackend.entity.AuditEntity;
import com.springboot.practiceimitateshopeebackend.entity.Order;
import com.springboot.practiceimitateshopeebackend.entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAllCancelledOrders();

    List<Transaction> getAllCompletedOrders();

}
