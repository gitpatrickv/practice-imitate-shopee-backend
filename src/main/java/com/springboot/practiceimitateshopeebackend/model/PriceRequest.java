package com.springboot.practiceimitateshopeebackend.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceRequest {

    @NotNull(message = "{product.id.must.not.be.null}")
    private Long id;
    @NotNull(message = "{this.field.cannot.be.empty}")
    @DecimalMin(value = "0.0", inclusive = false, message = "{price.required}")
    private Double price;
}
