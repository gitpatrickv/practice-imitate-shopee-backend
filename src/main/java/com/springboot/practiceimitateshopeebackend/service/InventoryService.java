package com.springboot.practiceimitateshopeebackend.service;

import com.springboot.practiceimitateshopeebackend.model.QuantityRequest;

public interface InventoryService {

    QuantityRequest addQuantity(QuantityRequest quantityRequest);

    QuantityRequest decreaseQuantity(QuantityRequest quantityRequest);
}
