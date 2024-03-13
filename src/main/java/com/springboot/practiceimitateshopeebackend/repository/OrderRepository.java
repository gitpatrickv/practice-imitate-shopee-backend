package com.springboot.practiceimitateshopeebackend.repository;

import com.springboot.practiceimitateshopeebackend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    void deleteAllByEmail(String email);

}


