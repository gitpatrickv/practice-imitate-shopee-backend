package com.springboot.practiceimitateshopeebackend.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuantityRequest {
    @Valid

    @NotNull(message = "{inventory.id.must.not.be.null}")
    private Long id;
    @NotNull(message = "{quantity.not.null}")
    private Long Quantity;
}
