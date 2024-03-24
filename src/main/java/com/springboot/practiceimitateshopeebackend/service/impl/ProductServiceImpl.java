package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.Cart;
import com.springboot.practiceimitateshopeebackend.entity.Inventory;
import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.entity.ProductVariation;
import com.springboot.practiceimitateshopeebackend.model.ProductModel;
import com.springboot.practiceimitateshopeebackend.model.StockRequest;
import com.springboot.practiceimitateshopeebackend.repository.CartRepository;
import com.springboot.practiceimitateshopeebackend.repository.InventoryRepository;
import com.springboot.practiceimitateshopeebackend.repository.ProductRepository;
import com.springboot.practiceimitateshopeebackend.repository.ProductVariationRepository;
import com.springboot.practiceimitateshopeebackend.security.JwtAuthenticationFilter;
import com.springboot.practiceimitateshopeebackend.service.ProductService;
import com.springboot.practiceimitateshopeebackend.utils.StringUtils;
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
    private final ProductVariationRepository productVariationRepository;
    private final ProductMapper mapper;

    @Override
    public ProductModel saveProduct(ProductModel model) {
        boolean isNew = productRepository.existsById(model.getProductId());
        Product product;
        String username = JwtAuthenticationFilter.CURRENT_USER;

        if(!isNew) {
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

            //this.updateCart(product);
            this.updateInventory(product);
            product.setLastModifiedBy(username);
        }
        Product savedProduct = productRepository.save(product);
        return mapper.mapProductEntityToProductModel(savedProduct);
    }

    private void updateInventory(Product product){
        List<Inventory> inventories = product.getInventory();

        if(inventories != null){
            for(Inventory inventory : inventories){
                inventory.setProduct(product);
            }
        }
    }
/*
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

 */

    @Override
    public void saveProductStocks(StockRequest stockRequest) {

        Optional<Product> product = productRepository.findById(stockRequest.getId());
        boolean isNew = inventoryRepository.existsByProduct_ProductId(stockRequest.getId());
        //Optional<Inventory> inventory = inventoryRepository.findByProduct_ProductId(stockRequest.getId());
        //Inventory inv;

        if (!isNew) {
            Inventory inv = new Inventory();
            inv.setProduct(product.get());
            inv.setProductName(product.get().getProductName());
            inv.setShopName(product.get().getShopName());
            inv.setPrice(stockRequest.getPrice());
            inv.setQuantity(stockRequest.getQuantity());
            inventoryRepository.save(inv);
        } else {
            log.info(StringUtils.PRODUCT_ALREADY_EXISTS);
        }
            /*
        else{
            inv = inventory.get();
            inv.setQuantity(inv.getQuantity() + stockRequest.getQuantity());
            inventoryRepository.save(inv);
        }
        */
    }

    @Override
    public void saveProductVariationStocks(StockRequest stockRequest) {
        Optional<ProductVariation> productVariation = productVariationRepository.findById(stockRequest.getId());
        boolean isNew = inventoryRepository.existsByProductVariation_VariationId(stockRequest.getId());

        if(!isNew){
            Inventory inv = new Inventory();
            inv.setProductVariation(productVariation.get());
            inv.setProduct(productVariation.get().getProduct());
            inv.setColor(productVariation.get().getColor());
            inv.setSize(productVariation.get().getSize());
            inv.setQuantity(stockRequest.getQuantity());
            inv.setPrice(stockRequest.getPrice());
            inv.setProductName(productVariation.get().getProduct().getProductName());
            inv.setShopName(productVariation.get().getProduct().getShopName());
            inventoryRepository.save(inv);
        }else{
            log.info(StringUtils.PRODUCT_ALREADY_EXISTS);
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
