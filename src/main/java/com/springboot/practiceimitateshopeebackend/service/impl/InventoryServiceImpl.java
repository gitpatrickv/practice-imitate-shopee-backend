package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.ColorVariation;
import com.springboot.practiceimitateshopeebackend.entity.Inventory;
import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.model.InventoryRequest;
import com.springboot.practiceimitateshopeebackend.repository.ColorVariationRepository;
import com.springboot.practiceimitateshopeebackend.repository.InventoryRepository;
import com.springboot.practiceimitateshopeebackend.repository.ProductRepository;
import com.springboot.practiceimitateshopeebackend.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final ColorVariationRepository colorVariationRepository;
    @Override
    public InventoryRequest addQuantity(InventoryRequest inventoryRequest) {

        Optional<Product> product = productRepository.findById(inventoryRequest.getProductId());
        Optional<ColorVariation> colorVariation = colorVariationRepository.findByColorAndProductId(inventoryRequest.getColor(), inventoryRequest.getProductId());

        Inventory inv = new Inventory();
                inv.setProduct(product.get());
                inv.setQuantity(inventoryRequest.getQuantity());
                inv.setProductName(product.get().getProductName());
                inv.setColorVariation(colorVariation.get());
                inv.setColor(colorVariation.get().getColor());
                inventoryRepository.save(inv);


        return InventoryRequest.builder()
                .productId(inventoryRequest.getProductId())
                .quantity(inventoryRequest.getQuantity())
                .color(inventoryRequest.getColor())
                .build();

    }

    @Override
    public InventoryRequest decreaseQuantity(InventoryRequest inventoryRequest) {
        return null;
    }

}
