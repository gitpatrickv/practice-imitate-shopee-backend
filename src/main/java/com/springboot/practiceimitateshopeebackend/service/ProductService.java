package com.springboot.practiceimitateshopeebackend.service;

import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.model.ProductModel;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductModel saveProduct(ProductModel model);
    List<Product> getAll(String search);
    Optional<ProductModel> getOneById(Long id);
    void delete(Long id);



}
