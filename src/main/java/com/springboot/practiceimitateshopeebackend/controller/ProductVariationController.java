package com.springboot.practiceimitateshopeebackend.controller;

import com.springboot.practiceimitateshopeebackend.model.ProductVariationRequest;
import com.springboot.practiceimitateshopeebackend.service.ProductVariationService;
import jakarta.validation.Valid;
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
    public void addVariation(@RequestBody @Valid ProductVariationRequest request){
        productVariationService.addVariation(request);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductVariation(@PathVariable Long id){
        productVariationService.deleteProductVariation(id);
    }

}
