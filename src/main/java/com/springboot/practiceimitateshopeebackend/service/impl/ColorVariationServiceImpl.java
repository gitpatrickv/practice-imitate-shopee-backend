package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.ColorVariation;
import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.model.ColorRequest;
import com.springboot.practiceimitateshopeebackend.repository.ColorVariationRepository;
import com.springboot.practiceimitateshopeebackend.repository.ProductRepository;
import com.springboot.practiceimitateshopeebackend.service.ColorVariationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ColorVariationServiceImpl implements ColorVariationService {

    private final ColorVariationRepository colorVariationRepository;
    private final ProductRepository productRepository;
    @Override
    public void addColorVariation(ColorRequest colorRequest) {

        //Optional<Product> product = productRepository.findById(colorRequest.getProductId());
        ColorVariation colorVariation = new ColorVariation();
        //colorVariation.setProductId(product.get().getProductId());
        colorVariation.setColor(colorRequest.getColor());
        colorVariationRepository.save(colorVariation);
    }
}
