package com.springboot.practiceimitateshopeebackend.controller;

import com.springboot.practiceimitateshopeebackend.model.StockRequest;
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


/*
    @PostMapping("/decreaseQty")
    @ResponseStatus(HttpStatus.OK)
    public ColorVariationRequest decreaseQuantity(@RequestBody @Valid ColorVariationRequest colorVariationRequest){
        return inventoryService.decreaseQuantity(colorVariationRequest);
    }

 */
}
