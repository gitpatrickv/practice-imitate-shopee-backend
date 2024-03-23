package com.springboot.practiceimitateshopeebackend.service;

import com.springboot.practiceimitateshopeebackend.model.InventoryRequest;

public interface InventoryService {

    InventoryRequest addQuantity(InventoryRequest inventoryRequest);

    InventoryRequest decreaseQuantity(InventoryRequest inventoryRequest);
}
