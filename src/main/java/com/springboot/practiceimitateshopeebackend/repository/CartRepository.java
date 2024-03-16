package com.springboot.practiceimitateshopeebackend.repository;

import com.springboot.practiceimitateshopeebackend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByProduct_ProductIdAndUserEmail(Long id, String email);
    void deleteByProduct_ProductIdAndUserEmail(Long id, String email);
    Optional<Cart> findByFilterTrueAndUserEmailAndCreatedBy(String email, String user);
    List<Cart> findAllByFilterTrueAndUserEmail(String email);
    void deleteAllByFilterTrueAndUserEmail(String email);

}
