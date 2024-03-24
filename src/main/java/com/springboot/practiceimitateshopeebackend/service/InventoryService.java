package com.springboot.practiceimitateshopeebackend.service;

import com.springboot.practiceimitateshopeebackend.model.QuantityRequest;

public interface InventoryService {

void addQuantity(QuantityRequest quantityRequest);

void decreaseQuantity(QuantityRequest quantityRequest);


}
