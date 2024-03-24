package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.ColorVariation;
import com.springboot.practiceimitateshopeebackend.entity.Inventory;
import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.model.ColorVariationRequest;
import com.springboot.practiceimitateshopeebackend.model.QuantityRequest;
import com.springboot.practiceimitateshopeebackend.repository.ColorVariationRepository;
import com.springboot.practiceimitateshopeebackend.repository.InventoryRepository;
import com.springboot.practiceimitateshopeebackend.repository.ProductRepository;
import com.springboot.practiceimitateshopeebackend.service.InventoryService;

import com.springboot.practiceimitateshopeebackend.utils.StringUtils;
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
    private final ColorVariationRepository colorVariationRepository;
    @Override
    public void addColorVariation(ColorVariationRequest colorVariationRequest) {

        Optional<Product> product = productRepository.findById(colorVariationRequest.getProductId());
        Optional<ColorVariation> colorVariation = colorVariationRepository.findByColor(colorVariationRequest.getColor());
        boolean isNew = inventoryRepository.existsByColorAndProduct_ProductId(colorVariationRequest.getColor(), colorVariationRequest.getProductId());

        if(!isNew) {
            Inventory inv = new Inventory();
            inv.setProduct(product.get());
            inv.setProductName(product.get().getProductName());
            inv.setShopName(product.get().getShopName());
            inv.setColorVariation(colorVariation.get());
            inv.setColor(colorVariation.get().getColor());
            inventoryRepository.save(inv);
        }
        else{
            log.info(StringUtils.PRODUCT_VARIATION_ALREADY_EXISTS);
        }
    }
    @Override
    public void addQuantity(QuantityRequest quantityRequest) {

        Optional<Product> product = productRepository.findById(quantityRequest.getProductId());
        boolean isNew = inventoryRepository.existsByProduct_ProductId(quantityRequest.getProductId());
        Optional<Inventory> inventory = inventoryRepository.findByProduct_ProductId(quantityRequest.getProductId());
        Inventory inv;

        if(!isNew) {
            inv = new Inventory();
            inv.setProduct(product.get());
            inv.setProductName(product.get().getProductName());
            inv.setShopName(product.get().getShopName());
            inv.setPrice(quantityRequest.getPrice());
            inv.setQuantity(quantityRequest.getQuantity());
            inventoryRepository.save(inv);
        }
        else{
            inv = inventory.get();
            inv.setQuantity(inv.getQuantity() + quantityRequest.getQuantity());
            inventoryRepository.save(inv);
        }
    }


}
