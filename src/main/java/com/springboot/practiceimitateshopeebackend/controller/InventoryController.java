package com.springboot.practiceimitateshopeebackend.controller;

import com.springboot.practiceimitateshopeebackend.model.ColorVariationRequest;
import com.springboot.practiceimitateshopeebackend.model.SaveRequest;
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
    @PostMapping("/variation/color/add")
    @ResponseStatus(HttpStatus.OK)
    void addColorVariation(@RequestBody @Valid ColorVariationRequest colorVariationRequest){
        inventoryService.addColorVariation(colorVariationRequest);
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public void save(@RequestBody @Valid SaveRequest saveRequest){
         inventoryService.save(saveRequest);
    }
/*
    @PostMapping("/decreaseQty")
    @ResponseStatus(HttpStatus.OK)
    public ColorVariationRequest decreaseQuantity(@RequestBody @Valid ColorVariationRequest colorVariationRequest){
        return inventoryService.decreaseQuantity(colorVariationRequest);
    }

 */
}
