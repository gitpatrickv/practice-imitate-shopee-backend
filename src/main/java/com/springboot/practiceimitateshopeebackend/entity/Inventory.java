package com.springboot.practiceimitateshopeebackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inventoryId;
    private Long quantity;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
