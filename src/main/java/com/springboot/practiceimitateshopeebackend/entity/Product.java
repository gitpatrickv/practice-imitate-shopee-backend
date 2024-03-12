package com.springboot.practiceimitateshopeebackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String shopName;
    private String productName;
    private Double price;
    private Long quantity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Cart> cart;
    
}
