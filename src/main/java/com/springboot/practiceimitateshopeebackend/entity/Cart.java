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
public class Cart extends AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_gen")
    @SequenceGenerator(name = "cart_gen", sequenceName = "cart_seq", allocationSize = 1)
    private Long cartId;
    private Long quantity;
    private Double price;
    private Double totalAmount;
    private String shopName;
    private String productName;
    private String color;
    private String size;
    private boolean filter;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @OneToMany(mappedBy = "cart")
    private List<Order> order;

}
