package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.model.ProductModel;
import com.springboot.practiceimitateshopeebackend.model.Response;
import com.springboot.practiceimitateshopeebackend.repository.ProductRepository;
import com.springboot.practiceimitateshopeebackend.service.ProductService;
import com.springboot.practiceimitateshopeebackend.utils.StringUtils;
import com.springboot.practiceimitateshopeebackend.utils.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper mapper;

    @Override
    public ProductModel saveProduct(ProductModel model) {

        boolean isNew = true;

        if(!isNew){
            ProductModel update = this.getOneById(model.getProductId()).get();

            if(model.getShopName() != null){
                update.setShopName(model.getShopName());
            }
            if(model.getProductName() != null){
                update.setProductName(model.getProductName());
            }
            if(model.getPrice() != null){
                update.setPrice(model.getPrice());
            }
            if(model.getQuantity() != null){
                update.setQuantity(model.getQuantity());
            }
        }
        Product product = mapper.mapProductModelToProductEntity(model);
        product.setCreatedBy(model.getShopName());
        product.setLastModifiedBy(model.getShopName());
        Product saveProduct = productRepository.save(product);
        return mapper.mapProductEntityToProductModel(saveProduct);
    }
    @Override
    public List<ProductModel> searchProduct(String search) {
        return productRepository.findByProductNameContainingIgnoreCaseOrShopNameContainingIgnoreCase(search, search)
                .stream()
                .map(mapper::mapProductEntityToProductModel)
                .toList();
    }
    @Override
    public Optional<ProductModel> getOneById(Long id) {
        return productRepository.findById(id).map(mapper::mapProductEntityToProductModel);
    }
    @Override
    public void delete(Long id) {
       productRepository.deleteById(id);
    }
}
