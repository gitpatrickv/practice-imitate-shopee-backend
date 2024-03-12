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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private String name;
    private String address;
    private String email;
    private String contactNumber;

    private Double price;
    private Double totalAmount;
    private String shopName;
    private String productName;
    private Long quantity;

    private Long productId;
    private String userId;

    private String paymentMethod;
    private String orderStatus;

    //@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
   // private List<Cart> cart;
/*
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart Cart;
 */


    //@ManyToOne
    //@JoinColumn(name = "user_id")
    //private User user;

    //@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    @ManyToMany
    @JoinTable(name = "order_list",
            joinColumns = {
            @JoinColumn(name = "order_id")
            },
            inverseJoinColumns = {
            @JoinColumn(name = "cart_id")
    })
    private List<Cart> cart;








}
