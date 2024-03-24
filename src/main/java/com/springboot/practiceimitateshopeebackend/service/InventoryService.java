package com.springboot.practiceimitateshopeebackend.service;

import com.springboot.practiceimitateshopeebackend.model.ColorVariationRequest;
import com.springboot.practiceimitateshopeebackend.model.QuantityRequest;
import com.springboot.practiceimitateshopeebackend.model.SaveRequest;

public interface InventoryService {

    void addColorVariation(ColorVariationRequest colorVariationRequest);

    //void save(SaveRequest saveRequest);

    void addQuantity(QuantityRequest quantityRequest);


}
