package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.mapper.ProductMapper;
import com.springboot.practiceimitateshopeebackend.model.ProductModel;
import com.springboot.practiceimitateshopeebackend.repository.ProductRepository;
import com.springboot.practiceimitateshopeebackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper mapper;

    @Override
    public ProductModel save(ProductModel model) {

        boolean isNew = true;

        if(!isNew){
            ProductModel update = this.getById(model.getProductId()).get();

            if(model.getShopName() != null){
                update.setShopName(model.getShopName());
            }
            if(model.getProductName() != null){
                update.setProductName(model.getProductName());
            }
            if(model.getProductAmount() != null){
                update.setProductAmount(model.getProductAmount());
            }
            if(model.getQuantity() != null){
                update.setQuantity(model.getQuantity());
            }
        }

        Product product = mapper.mapProductModelToProductEntity(model);
        Product saveProduct = productRepository.save(product);
        return mapper.mapProductEntityToProductModel(saveProduct);

    }

    @Override
    public Optional<ProductModel> getById(Long id) {
        return productRepository.findById(id).map(mapper::mapProductEntityToProductModel);
    }
}
