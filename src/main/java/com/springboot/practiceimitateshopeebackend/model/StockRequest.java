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
public class StockRequest {

    @Valid

    @NotNull(message = "{product.id.must.not.be.null}")
    private Long id;

    @NotNull(message = "{price.not.null}")
    private Double price;

    @NotNull(message = "{quantity.not.null}")
    private Long quantity;
}
