package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.Cart;
import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.model.ProductModel;
import com.springboot.practiceimitateshopeebackend.repository.CartRepository;
import com.springboot.practiceimitateshopeebackend.repository.ProductRepository;
import com.springboot.practiceimitateshopeebackend.security.JwtAuthenticationFilter;
import com.springboot.practiceimitateshopeebackend.service.ProductService;
import com.springboot.practiceimitateshopeebackend.utils.mapper.ProductMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final ProductMapper mapper;

    @Override
    public ProductModel saveProduct(ProductModel model) {
        boolean isNew = !productRepository.existsById(model.getProductId());
        Product product;
        String username = JwtAuthenticationFilter.CURRENT_USER;

        if(isNew) {
            product = mapper.mapProductModelToProductEntity(model);
            product.setCreatedBy(username);
        } else {
            product = productRepository.findById(model.getProductId()).get();
            if (model.getShopName() != null) {
                product.setShopName(model.getShopName());
            }
            if (model.getProductName() != null) {
                product.setProductName(model.getProductName());
            }
            if (model.getPrice() != null) {
                product.setPrice(model.getPrice());
            }
            if (model.getQuantity() != null) {
                product.setQuantity(model.getQuantity());
            }
            updateCart(product);
            product.setLastModifiedBy(username);
        }
        Product savedProduct = productRepository.save(product);
        return mapper.mapProductEntityToProductModel(savedProduct);
    }

    private void updateCart(Product product) {
        List<Cart> carts = product.getCart();
        if (carts != null) {
            for (Cart cart : carts) {
                cart.setProductName(product.getProductName());
                cart.setShopName(product.getShopName());
                cart.setPrice(product.getPrice());
                cart.setTotalAmount(cart.getQuantity() * product.getPrice());
                cartRepository.save(cart);
            }
        }
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
