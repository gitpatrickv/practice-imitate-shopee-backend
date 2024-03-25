package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.Inventory;
import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.entity.ProductVariation;
import com.springboot.practiceimitateshopeebackend.model.ProductVariationRequest;
import com.springboot.practiceimitateshopeebackend.repository.InventoryRepository;
import com.springboot.practiceimitateshopeebackend.repository.ProductRepository;
import com.springboot.practiceimitateshopeebackend.repository.ProductVariationRepository;
import com.springboot.practiceimitateshopeebackend.service.ProductVariationService;
import com.springboot.practiceimitateshopeebackend.utils.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductVariationServiceImpl implements ProductVariationService {

    private final ProductVariationRepository productVariationRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;


    @Override
    public void addVariation(ProductVariationRequest request) {

        Optional<Product> product = productRepository.findById(request.getProductId());
        boolean isNew = productVariationRepository.existsByColorAndSizeAndProduct_ProductId(request.getColor(), request.getSize(), request.getProductId());

        if (!isNew) {
            ProductVariation productVariation = new ProductVariation();
            productVariation.setProduct(product.get());
            productVariation.setColor(request.getColor());
            productVariation.setSize(request.getSize());
            ProductVariation variation = productVariationRepository.save(productVariation);

            Inventory inventory = Inventory.builder()
                    .productVariation(variation)
                    .product(variation.getProduct())
                    .color(variation.getColor())
                    .size(variation.getSize())
                    .productName(variation.getProduct().getProductName())
                    .shopName(variation.getProduct().getShopName())
                    .price(request.getPrice())
                    .quantity(request.getQuantity())
                    .build();
            inventoryRepository.save(inventory);

        } else {
            log.info(StringUtils.PRODUCT_VARIATION_ALREADY_EXISTS);
        }
    }

    @Override
    public void deleteProductVariation(Long id) {
        productVariationRepository.deleteById(id);
    }

}
