package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.Inventory;
import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.entity.ProductVariation;
import com.springboot.practiceimitateshopeebackend.model.StockRequest;

import com.springboot.practiceimitateshopeebackend.repository.InventoryRepository;
import com.springboot.practiceimitateshopeebackend.repository.ProductRepository;
import com.springboot.practiceimitateshopeebackend.repository.ProductVariationRepository;
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
    private final ProductVariationRepository productVariationRepository;



    @Override
    public void saveProductStocks(StockRequest stockRequest) {

        Optional<Product> product = productRepository.findById(stockRequest.getId());
        boolean isNew = inventoryRepository.existsByProduct_ProductId(stockRequest.getId());
        //Optional<Inventory> inventory = inventoryRepository.findByProduct_ProductId(stockRequest.getId());
        //Inventory inv;

        if(!isNew) {
            Inventory inv = new Inventory();
            inv.setProduct(product.get());
            inv.setProductName(product.get().getProductName());
            inv.setShopName(product.get().getShopName());
            inv.setPrice(stockRequest.getPrice());
            inv.setQuantity(stockRequest.getQuantity());
            inventoryRepository.save(inv);
        }else{
            log.info(StringUtils.PRODUCT_ALREADY_EXISTS);
        }

        /*
        else{
            inv = inventory.get();
            inv.setQuantity(inv.getQuantity() + stockRequest.getQuantity());
            inventoryRepository.save(inv);
        }
        */
    }

    @Override
    public void saveProductVariationStocks(StockRequest stockRequest) {
        Optional<ProductVariation> productVariation = productVariationRepository.findById(stockRequest.getId());
        boolean isNew = inventoryRepository.existsByProductVariation_VariationId(stockRequest.getId());

        if(!isNew){
            Inventory inv = new Inventory();
            inv.setProductVariation(productVariation.get());
            inv.setProduct(productVariation.get().getProduct());
            inv.setColor(productVariation.get().getColor());
            inv.setSize(productVariation.get().getSize());
            inv.setQuantity(stockRequest.getQuantity());
            inv.setPrice(stockRequest.getPrice());
            inv.setProductName(productVariation.get().getProduct().getProductName());
            inv.setShopName(productVariation.get().getProduct().getShopName());
            inventoryRepository.save(inv);
        }else{
            log.info(StringUtils.PRODUCT_ALREADY_EXISTS);
        }

    }




}
