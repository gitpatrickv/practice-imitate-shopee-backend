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

    @PostMapping("/quantity/add")
    @ResponseStatus(HttpStatus.OK)
    public void addQuantity(@RequestBody @Valid QuantityRequest quantityRequest){
        inventoryService.addQuantity(quantityRequest);
    }

    @PostMapping("/quantity/decrease")
    @ResponseStatus(HttpStatus.OK)
    public void decreaseQuantity(@RequestBody @Valid QuantityRequest quantityRequest){
        inventoryService.decreaseQuantity(quantityRequest);
    }


}
