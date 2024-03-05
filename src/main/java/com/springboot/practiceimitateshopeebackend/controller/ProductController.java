package com.springboot.practiceimitateshopeebackend.controller;

import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.model.ProductModel;
import com.springboot.practiceimitateshopeebackend.model.Response;
import com.springboot.practiceimitateshopeebackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PutMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public ProductModel saveProduct(@RequestBody ProductModel model){
        return productService.saveProduct(model);
    }
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll(@RequestParam (value = "keyword") String search ){
        return productService.getAll(search);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductModel> getOneById(@PathVariable Long id){
        return productService.getOneById(id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response delete(@PathVariable Long id){
        return productService.delete(id);
    }
}
