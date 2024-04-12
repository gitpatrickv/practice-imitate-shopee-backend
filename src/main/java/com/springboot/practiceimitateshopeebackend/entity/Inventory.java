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
@Table(name = "inventory")
public class Inventory extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_gen")
    @SequenceGenerator(name = "inventory_gen", sequenceName = "inventory_seq", allocationSize = 1)
    private Long inventoryId;
    private Long quantity;
    private String productName;
    private String shopName;
    private Double price;
    private String skuCode;
    private String color;
    private String size;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_variation_id")
    private ProductVariation productVariation;

    @OneToMany(mappedBy = "inventory",  cascade = CascadeType.ALL)
    private List<Cart> cart;

    @OneToMany(mappedBy = "inventory",  cascade = CascadeType.ALL)
    private List<ProductImage> productImage;

}
