package com.springboot.practiceimitateshopeebackend.controller;

import com.springboot.practiceimitateshopeebackend.model.ColorRequest;
import com.springboot.practiceimitateshopeebackend.service.ColorVariationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/variation/color")
@RequiredArgsConstructor
public class ColorVariationController {

    private final ColorVariationService colorVariationService;
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void addColorVariation(@RequestBody ColorRequest colorRequest){
        colorVariationService.addColorVariation(colorRequest);
    }
}
