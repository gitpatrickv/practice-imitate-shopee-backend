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
public class QuantityRequest {

    @Valid

    @NotNull(message = "{product.id.must.not.be.null}")
    private Long productId;

    @NotNull(message = "{price.not.null}")
    private Double price;

    //@NotNull(message = "{inventory.id.must.not.be.null}")
    //private Long inventoryId;

    @NotNull(message = "{quantity.not.null}")
    private Long quantity;
}
