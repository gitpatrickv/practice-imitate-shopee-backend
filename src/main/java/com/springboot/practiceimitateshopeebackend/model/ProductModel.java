package com.springboot.practiceimitateshopeebackend.model;

import com.springboot.practiceimitateshopeebackend.validation.marker.OnCreate;
import com.springboot.practiceimitateshopeebackend.validation.marker.OnUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductModel {

    @Valid

    @NotNull(message = "{product.id.must.not.be.null}")
    private Long productId;
    @NotBlank(message = "{shop.name.required}")
    private String shopName;
    @NotBlank(message = "{product.name.required}")
    private String productName;
    @NotNull(message = "{price.not.null}")
    private Double price;
    @NotNull(message = "{quantity.not.null}")
    private Long quantity;
}
