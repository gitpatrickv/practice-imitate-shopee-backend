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
@Table(name = "colorVariation")
public class ColorVariation{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long colorId;
    private Long productId;

    @Enumerated(EnumType.STRING)
    private Color color;

    @OneToMany(mappedBy = "colorVariation")
    private List<Inventory> inventory;

}
