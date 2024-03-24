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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Inventory> inventory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductVariation> productVariation;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

}
