package com.springboot.practiceimitateshopeebackend.utils.mapper;

import com.springboot.practiceimitateshopeebackend.entity.Cart;
import com.springboot.practiceimitateshopeebackend.model.CartModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CartMapper {

    private final ModelMapper mapper = new ModelMapper();

    public CartModel mapCartEntityToCartModel(Cart cart){
        return mapper.map(cart, CartModel.class);
    }

    public Cart mapCartModelToCartEntity(CartModel cartModel){
        return mapper.map(cartModel, Cart.class);
    }

}
