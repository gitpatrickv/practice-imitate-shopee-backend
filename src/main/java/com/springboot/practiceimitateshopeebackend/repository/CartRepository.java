package com.springboot.practiceimitateshopeebackend.repository;

import com.springboot.practiceimitateshopeebackend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    //Optional<Cart> findByEmailAndProductId(String email, Long id2);
}
