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
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;
    private Long quantity;
    private Double productAmount;
    //@OneToMany(mappedBy = "cart")
    //private List<Product> product;

    @OneToOne
    private Product product;
    @OneToOne
    private User user;

    @Transient
    private Double total;

}
