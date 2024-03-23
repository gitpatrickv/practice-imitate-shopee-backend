package com.springboot.practiceimitateshopeebackend.model;

import com.springboot.practiceimitateshopeebackend.entity.constants.Color;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColorRequest {

    @Valid

    //@NotNull(message = "{product.id.must.not.be.null}")
    //private Long productId;
    @Enumerated(EnumType.STRING)
    private Color color;

}
