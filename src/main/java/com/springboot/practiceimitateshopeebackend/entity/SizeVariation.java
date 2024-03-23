package com.springboot.practiceimitateshopeebackend.entity;

import com.springboot.practiceimitateshopeebackend.entity.constants.Size;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sizeVariation")
public class SizeVariation{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sizeId;

    @Enumerated(EnumType.STRING)
    private Size size;


    @OneToMany(mappedBy = "sizeVariation")
    private List<SizeColorVariation> sizeColorVariation;
}
