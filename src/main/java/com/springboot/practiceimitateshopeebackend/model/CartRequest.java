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
public class CartRequest {

   @Valid

   @NotNull(message = "{inventory.id.must.not.be.null}")
   private Long id;
   @NotNull(message = "{quantity.not.null}")
   private Long quantity;

}
