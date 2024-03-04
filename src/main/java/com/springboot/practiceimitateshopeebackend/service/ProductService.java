package com.springboot.practiceimitateshopeebackend.service;

import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.model.ProductModel;

import java.util.Optional;

public interface ProductService {

    ProductModel save(ProductModel model);

    Optional<ProductModel> getById(Long id);
}
