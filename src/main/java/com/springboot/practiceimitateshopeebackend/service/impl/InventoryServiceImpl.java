package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.Inventory;
import com.springboot.practiceimitateshopeebackend.model.PriceRequest;
import com.springboot.practiceimitateshopeebackend.model.QuantityRequest;
import com.springboot.practiceimitateshopeebackend.repository.InventoryRepository;
import com.springboot.practiceimitateshopeebackend.repository.ProductRepository;
import com.springboot.practiceimitateshopeebackend.repository.ProductVariationRepository;
import com.springboot.practiceimitateshopeebackend.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final ProductVariationRepository productVariationRepository;


    @Override
    public void addQuantity(QuantityRequest quantityRequest) {

        Optional<Inventory> inventory = inventoryRepository.findById(quantityRequest.getId());

        Inventory inv = inventory.get();
        inv.setQuantity(inv.getQuantity() + quantityRequest.getQuantity());
        inventoryRepository.save(inv);
    }

    @Override
    public void decreaseQuantity(QuantityRequest quantityRequest) {
        Optional<Inventory> inventory = inventoryRepository.findById(quantityRequest.getId());

        Inventory inv = inventory.get();
        inv.setQuantity(inv.getQuantity() - quantityRequest.getQuantity());
        inventoryRepository.save(inv);
    }

    @Override
    public void updatePrice(PriceRequest priceRequest) {
        Optional<Inventory> inventory = inventoryRepository.findById(priceRequest.getId());

        Inventory inv = inventory.get();
        inv.setPrice(priceRequest.getPrice());
        inventoryRepository.save(inv);
    }
}







