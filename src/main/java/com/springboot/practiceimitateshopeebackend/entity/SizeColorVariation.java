package com.springboot.practiceimitateshopeebackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sizeColorVariation")
public class SizeColorVariation{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long variationId;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "sizeVariation_id")
    private SizeVariation sizeVariation;

    private Long quantity;
    private Double price;
}
