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
    private Double price;
    private Double totalAmount;
    private String shopName;
    private String productName;
    private boolean filter;

    //@OneToMany(mappedBy = "cart")
    @ManyToOne
    @JoinColumn(name = "product_id")
    //@OneToOne
    private Product product;

    //@OneToOne
    //private Product product;
    //@OneToMany(mappedBy = "cart")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //@ManyToOne
   // @JoinColumn(name = "order_id")
    //private Order order;


}
