package com.springboot.practiceimitateshopeebackend.entity;

import com.springboot.practiceimitateshopeebackend.entity.constants.Color;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inventoryId;
    private Long quantity;
    private String productName;

    @Enumerated(EnumType.STRING)
    private Color color;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private ColorVariation colorVariation;

    @OneToMany(mappedBy = "inventory")
    private List<Cart> cart;

    @OneToMany(mappedBy = "inventory")
    private List<SizeColorVariation> sizeColorVariation;

}
