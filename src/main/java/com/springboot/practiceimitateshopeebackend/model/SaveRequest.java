package com.springboot.practiceimitateshopeebackend.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Deprecated
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveRequest {

    @Valid

    @NotNull(message = "{product.id.must.not.be.null}")
    private Long productId;

    @NotNull(message = "{quantity.not.null}")
    private Long quantity;
}
