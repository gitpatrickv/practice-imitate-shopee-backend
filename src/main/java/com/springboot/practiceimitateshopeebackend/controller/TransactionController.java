package com.springboot.practiceimitateshopeebackend.controller;

import com.springboot.practiceimitateshopeebackend.entity.Transaction;
import com.springboot.practiceimitateshopeebackend.service.TransactionService;
import com.springboot.practiceimitateshopeebackend.service.impl.TransactionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/cancelledOrders")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getAllCancelledOrders(){
        return transactionService.getAllCancelledOrders();
    }

    @GetMapping("/completeOrder")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getAllCompletedOrders(){
        return transactionService.getAllCompletedOrders();
    }
}
