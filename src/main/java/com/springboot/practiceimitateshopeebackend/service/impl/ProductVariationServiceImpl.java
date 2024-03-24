package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.entity.ProductVariation;
import com.springboot.practiceimitateshopeebackend.model.ProductVariationRequest;
import com.springboot.practiceimitateshopeebackend.repository.ProductRepository;
import com.springboot.practiceimitateshopeebackend.repository.ProductVariationRepository;
import com.springboot.practiceimitateshopeebackend.service.ProductVariationService;
import com.springboot.practiceimitateshopeebackend.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductVariationServiceImpl implements ProductVariationService {

    private final ProductVariationRepository productVariationRepository;
    private final ProductRepository productRepository;


    @Override
    public void addVariation(ProductVariationRequest request) {

        Optional<Product> product = productRepository.findById(request.getProductId());
        boolean isNew = productVariationRepository.existsByColorAndSizeAndProduct_ProductId(request.getColor(), request.getSize(), request.getProductId());

        if(!isNew){
            ProductVariation productVariation = new ProductVariation();
            productVariation.setProduct(product.get());
            productVariation.setColor(request.getColor());
            productVariation.setSize(request.getSize());
            productVariationRepository.save(productVariation);
        }
        else{
            log.info(StringUtils.PRODUCT_VARIATION_ALREADY_EXISTS);
        }
    }
}
