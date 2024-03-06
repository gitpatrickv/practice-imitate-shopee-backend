package com.springboot.practiceimitateshopeebackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String shopName;
    private String productName;
    private Double productAmount;
    private Integer quantity;

    //@ManyToOne
    //@JoinColumn(name = "cart_id")       //cascade = CascadeType.ALL ???
    @OneToOne
    private Cart cart;
}
