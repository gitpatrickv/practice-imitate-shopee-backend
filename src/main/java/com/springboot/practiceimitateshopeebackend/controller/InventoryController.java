package com.springboot.practiceimitateshopeebackend.controller;

import com.springboot.practiceimitateshopeebackend.model.InventoryRequest;
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
    InventoryRequest addQuantity(@RequestBody @Valid InventoryRequest inventoryRequest){
        return inventoryService.addQuantity(inventoryRequest);
    }

    @PostMapping("/decreaseQty")
    @ResponseStatus(HttpStatus.OK)
    public InventoryRequest decreaseQuantity(@RequestBody @Valid InventoryRequest inventoryRequest){
        return inventoryService.decreaseQuantity(inventoryRequest);
    }
}
