package com.springboot.practiceimitateshopeebackend.service;

import com.springboot.practiceimitateshopeebackend.model.ColorVariationRequest;

public interface InventoryService {

    void addColorVariation(ColorVariationRequest colorVariationRequest);

    ColorVariationRequest decreaseQuantity(ColorVariationRequest colorVariationRequest);
}
