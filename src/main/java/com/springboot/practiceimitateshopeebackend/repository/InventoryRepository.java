package com.springboot.practiceimitateshopeebackend.repository;

import com.springboot.practiceimitateshopeebackend.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Boolean existsByProduct_ProductId(Long productId);

    Optional<Inventory> findByProduct_ProductId(Long id);

    Boolean existsByProductVariation_VariationId(Long id);

}
