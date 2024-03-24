package com.springboot.practiceimitateshopeebackend.service;

import com.springboot.practiceimitateshopeebackend.model.ProductVariationRequest;
import com.springboot.practiceimitateshopeebackend.model.StockRequest;

public interface ProductVariationService {

    void addVariation(ProductVariationRequest request);
    void saveProductVariationStocks(StockRequest stockRequest);

}
