package com.springboot.practiceimitateshopeebackend.controller;

import com.springboot.practiceimitateshopeebackend.model.ProductVariationRequest;
import com.springboot.practiceimitateshopeebackend.service.ProductVariationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/variation")
@RequiredArgsConstructor
public class ProductVariationController {

    private final ProductVariationService productVariationService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void addVariation(@RequestBody ProductVariationRequest request){
        productVariationService.addVariation(request);
    }
}
