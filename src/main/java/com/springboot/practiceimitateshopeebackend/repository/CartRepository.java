package com.springboot.practiceimitateshopeebackend.repository;

import com.springboot.practiceimitateshopeebackend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByInventory_InventoryIdAndUserEmail(Long id, String email);

    void deleteByInventory_InventoryIdAndUserEmail(Long id, String email);
    void deleteAllByUserEmail(String email);

}
