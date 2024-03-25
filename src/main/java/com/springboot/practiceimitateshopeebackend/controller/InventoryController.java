package com.springboot.practiceimitateshopeebackend.controller;

import com.springboot.practiceimitateshopeebackend.model.QuantityRequest;
import com.springboot.practiceimitateshopeebackend.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    @PostMapping("/addQuantity")
    @ResponseStatus(HttpStatus.OK)
    QuantityRequest addQuantity(@RequestBody @Valid QuantityRequest quantityRequest){
        return inventoryService.addQuantity(quantityRequest);
    }

    @PostMapping("/decreaseQty")
    @ResponseStatus(HttpStatus.OK)
    public QuantityRequest decreaseQuantity(@RequestBody @Valid QuantityRequest quantityRequest){
        return inventoryService.decreaseQuantity(quantityRequest);
    }
}
