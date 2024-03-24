package com.springboot.practiceimitateshopeebackend.repository;

import com.springboot.practiceimitateshopeebackend.entity.Inventory;
import com.springboot.practiceimitateshopeebackend.entity.constants.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Boolean existsByColorAndProduct_ProductId(Color color, Long id);

    Optional<Inventory> findByInventoryIdAndProduct_ProductId(Long id, Long productId);

    Boolean existsByProduct_ProductId(Long productId);

    Optional<Inventory> findByProduct_ProductId(Long id);

}
