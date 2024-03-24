package com.springboot.practiceimitateshopeebackend.controller;

import com.springboot.practiceimitateshopeebackend.model.ProductModel;
import com.springboot.practiceimitateshopeebackend.model.StockRequest;
import com.springboot.practiceimitateshopeebackend.service.ProductService;
import jakarta.validation.Valid;
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

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public ProductModel saveProduct(@RequestBody @Valid ProductModel model){
        return productService.saveProduct(model);
    }
    @PostMapping("/stock/add")
    @ResponseStatus(HttpStatus.OK)
    public void saveProductStocks(@RequestBody @Valid StockRequest stockRequest) {
        productService.saveProductStocks(stockRequest);
    }
    @PostMapping("/stock/variation/add")
    @ResponseStatus(HttpStatus.OK)
    public void saveProductVariationStocks(@RequestBody StockRequest stockRequest) {
        productService.saveProductVariationStocks(stockRequest);
    }
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductModel> searchProduct(@RequestParam (value = "keyword") String search ){
        return productService.searchProduct(search);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductModel> getOneById(@PathVariable Long id){
        return productService.getOneById(id);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }
}
