package com.springboot.practiceimitateshopeebackend.utils.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.model.ProductModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductMapper {
    private final ModelMapper mapper = new ModelMapper();

    public ProductModel mapProductEntityToProductModel(Product product){
        return mapper.map(product, ProductModel.class);
    }

    public Product mapProductModelToProductEntity(ProductModel productModel){
        return mapper.map(productModel, Product.class);
    }
}


