package com.springboot.practiceimitateshopeebackend.model;

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
public class ProductVariationRequest {

    @Valid

    @NotNull(message = "{product.id.must.not.be.null}")
    private Long productId;
    @NotNull(message = "{color.required}")
    private String color;
    @NotNull(message = "{size.required}")
    private String size;
}
