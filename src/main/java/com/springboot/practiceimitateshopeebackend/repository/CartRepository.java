package com.springboot.practiceimitateshopeebackend.repository;

import com.springboot.practiceimitateshopeebackend.entity.Cart;
import com.springboot.practiceimitateshopeebackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByProduct_ProductId(Long id);

    //Optional<Cart> findByEmailAndProductId(String email, Long id2);

    //Boolean existsByProduct_Id(Long id);

    //Boolean existsByShopNameAndProductName(String string1, String string2);

    //Optional<Cart> findByShopNameAndProductName(String string1, String string2);




}
