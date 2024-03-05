package com.springboot.practiceimitateshopeebackend.service;

import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.model.ProductModel;
import com.springboot.practiceimitateshopeebackend.model.Response;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductModel saveProduct(ProductModel model);
    List<ProductModel> getAll(String search);
    Optional<ProductModel> getOneById(Long id);
    Response delete(Long id);



}
