package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.Cart;
import com.springboot.practiceimitateshopeebackend.entity.Inventory;
import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.entity.User;
import com.springboot.practiceimitateshopeebackend.model.ProductModel;
import com.springboot.practiceimitateshopeebackend.repository.*;
import com.springboot.practiceimitateshopeebackend.security.JwtAuthenticationFilter;
import com.springboot.practiceimitateshopeebackend.service.ProductService;
import com.springboot.practiceimitateshopeebackend.utils.mapper.ProductMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductMapper mapper;

    @Override
    public ProductModel saveProduct(ProductModel model) {
        boolean isNew = productRepository.existsById(model.getProductId());
        Product product;
        String username = JwtAuthenticationFilter.CURRENT_USER;
        User user = userRepository.findById(username).get();

        if(!isNew) {
            product = mapper.mapProductModelToProductEntity(model);
            product.setUser(user);
        } else {
            product = productRepository.findById(model.getProductId()).get();
            if (model.getShopName() != null) {
                product.setShopName(model.getShopName());
            }
            if (model.getProductName() != null) {
                product.setProductName(model.getProductName());
            }
            if(model.getProductDescription() != null){
                product.setProductDescription(model.getProductDescription());
            }
            this.updateInventory(product);
        }

        Product savedProduct = productRepository.save(product);

        boolean isExists = inventoryRepository.existsByProduct_ProductId(savedProduct.getProductId());
        if(!isExists) {
            Inventory inventory = Inventory.builder()
                    .product(savedProduct)
                    .price(model.getPrice())
                    .quantity(model.getQuantity())
                    .productName(savedProduct.getProductName())
                    .shopName(savedProduct.getShopName())
                    .build();
            inventoryRepository.save(inventory);
        }
        return mapper.mapProductEntityToProductModel(savedProduct);
    }

    private void updateInventory(Product product){
        List<Inventory> inventories = product.getInventory();

        if(inventories != null){
            for(Inventory inventory : inventories){
                inventory.setProduct(product);
                inventory.setProductName(product.getProductName());
                inventory.setShopName(product.getShopName());
                inventoryRepository.save(inventory);

                List<Cart> carts = inventory.getCart();
                if(carts != null){
                    for(Cart cart : carts){
                        cart.setProductName(inventory.getProductName());
                        cart.setShopName(inventory.getShopName());
                        cartRepository.save(cart);
                    }
                }
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
