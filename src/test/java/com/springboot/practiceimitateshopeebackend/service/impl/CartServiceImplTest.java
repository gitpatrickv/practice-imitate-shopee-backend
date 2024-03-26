package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.Cart;
import com.springboot.practiceimitateshopeebackend.entity.Inventory;
import com.springboot.practiceimitateshopeebackend.entity.Product;
import com.springboot.practiceimitateshopeebackend.entity.User;
import com.springboot.practiceimitateshopeebackend.model.CartRequest;
import com.springboot.practiceimitateshopeebackend.repository.CartRepository;
import com.springboot.practiceimitateshopeebackend.repository.InventoryRepository;
import com.springboot.practiceimitateshopeebackend.repository.ProductRepository;
import com.springboot.practiceimitateshopeebackend.repository.UserRepository;
import com.springboot.practiceimitateshopeebackend.security.JwtAuthenticationFilter;
import com.springboot.practiceimitateshopeebackend.utils.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CartServiceImplTest {

    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    CartRepository cartRepository;

    @Mock
    UserRepository userRepository;
    @Mock
    InventoryRepository inventoryRepository;

    @Mock
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addQuantityIfProductAlreadyExistsInTheCartTest(){

        CartRequest cartRequest = new CartRequest();
        cartRequest.setId(1L);
        cartRequest.setQuantity(6L);

        User user = new User();
        user.setEmail(JwtAuthenticationFilter.CURRENT_USER);

        Inventory inventory = new Inventory();
        inventory.setInventoryId(1L);
        inventory.setPrice(100.0);
        inventory.setQuantity(10L);

        Cart existingCart = new Cart();
        existingCart.setCreatedBy(user.getEmail());
        existingCart.setInventory(inventory);
        existingCart.setQuantity(4L);

        when(userRepository.findById(user.getEmail())).thenReturn(Optional.of(user));
        when(inventoryRepository.findById(cartRequest.getId())).thenReturn(Optional.of(inventory));
        when(cartRepository.findByInventory_InventoryIdAndUserEmail(cartRequest.getId(), user.getEmail())).thenReturn(Optional.of(existingCart));

        cartService.addToCart(cartRequest);

        verify(cartRepository, times(1)).save(any(Cart.class));

        Assertions.assertThat(existingCart.getQuantity()).isLessThanOrEqualTo(inventory.getQuantity());
        Assertions.assertThat(existingCart.getInventory().getInventoryId()).isEqualTo(cartRequest.getId());
        Assertions.assertThat(inventory.getQuantity()).isEqualTo(10L);
        Assertions.assertThat(existingCart.getQuantity()).isEqualTo(10L);
        Assertions.assertThat(existingCart.getTotalAmount()).isEqualTo(1000);

    }
    @Test
    public void addNewProductToCartIfProductDoesntExistsInTheCart(){

        CartRequest cartRequest = new CartRequest();
        cartRequest.setId(1L);
        cartRequest.setQuantity(6L);

        User user = new User();
        user.setEmail(JwtAuthenticationFilter.CURRENT_USER);

        Inventory inventory = new Inventory();
        inventory.setInventoryId(1L);
        inventory.setQuantity(10L);
        inventory.setPrice(100.0);
        inventory.setShopName("SHOP NAME");
        inventory.setProductName("PRODUCT NAME");
        inventory.setColor("BLUE");
        inventory.setSize("MEDIUM");

        Cart newCart = new Cart();
        newCart.setQuantity(cartRequest.getQuantity());
        newCart.setPrice(inventory.getPrice());
        newCart.setTotalAmount(inventory.getPrice() * cartRequest.getQuantity());
        newCart.setShopName(inventory.getShopName());
        newCart.setProductName(inventory.getProductName());
        newCart.setColor(inventory.getColor());
        newCart.setSize(inventory.getSize());
        newCart.setUser(user);

        when(userRepository.findById(user.getEmail())).thenReturn(Optional.of(user));
        when(inventoryRepository.findById(cartRequest.getId())).thenReturn(Optional.of(inventory));

        cartService.addToCart(cartRequest);

        verify(cartRepository, times(1)).save(any(Cart.class));

        Assertions.assertThat(newCart.getQuantity()).isLessThanOrEqualTo(inventory.getQuantity());
        Assertions.assertThat(newCart.getShopName()).isEqualTo(inventory.getShopName());
        Assertions.assertThat(newCart.getProductName()).isEqualTo(inventory.getProductName());
        Assertions.assertThat(newCart.getPrice()).isEqualTo(inventory.getPrice());
        Assertions.assertThat(newCart.getQuantity()).isEqualTo(cartRequest.getQuantity());
        Assertions.assertThat(newCart.getTotalAmount()).isEqualTo(600);
        Assertions.assertThat(newCart.getSize()).isEqualTo("MEDIUM");
        Assertions.assertThat(newCart.getColor()).isEqualTo("BLUE");
        Assertions.assertThat(newCart.getUser()).isEqualTo(user);


    }

    @Test
    public void increaseProductQuantityInTheCartList(){

        Long id = 1L;

        User user = new User();
        user.setEmail(JwtAuthenticationFilter.CURRENT_USER);

        Inventory inventory = new Inventory();
        inventory.setInventoryId(1L);
        inventory.setQuantity(10L);
        inventory.setPrice(100.0);

        Cart existingCart = new Cart();
        existingCart.setInventory(inventory);
        existingCart.setCreatedBy(JwtAuthenticationFilter.CURRENT_USER);
        existingCart.setQuantity(9L);

        when(userRepository.findById(user.getEmail())).thenReturn(Optional.of(user));
        when(inventoryRepository.findById(id)).thenReturn(Optional.of(inventory));
        when(cartRepository.findByInventory_InventoryIdAndUserEmail(id, user.getEmail())).thenReturn(Optional.of(existingCart));

        cartService.increaseQuantity(id);

        verify(cartRepository, times(1)).save(any(Cart.class));

        Assertions.assertThat(existingCart.getQuantity()).isLessThanOrEqualTo(inventory.getQuantity());
        Assertions.assertThat(existingCart.getCreatedBy()).isEqualTo(user.getEmail());
        Assertions.assertThat(existingCart.getQuantity()).isEqualTo(10L);
        Assertions.assertThat(existingCart.getTotalAmount()).isEqualTo(1000);

    }

    @Test
    public void decreaseProductQuantityInTheCartList(){

        Long id = 1L;

        User user = new User();
        user.setEmail(JwtAuthenticationFilter.CURRENT_USER);

        Inventory inventory = new Inventory();
        inventory.setInventoryId(1L);
        inventory.setQuantity(10L);
        inventory.setPrice(100.0);

        Cart existingCart = new Cart();
        existingCart.setInventory(inventory);
        existingCart.setCreatedBy(JwtAuthenticationFilter.CURRENT_USER);
        existingCart.setQuantity(10L);

        when(userRepository.findById(user.getEmail())).thenReturn(Optional.of(user));
        when(inventoryRepository.findById(id)).thenReturn(Optional.of(inventory));
        when(cartRepository.findByInventory_InventoryIdAndUserEmail(id, user.getEmail())).thenReturn(Optional.of(existingCart));

        cartService.decreaseQuantity(id);

        verify(cartRepository, times(1)).save(any(Cart.class));

        Assertions.assertThat(existingCart.getQuantity()).isLessThan(inventory.getQuantity());
        Assertions.assertThat(existingCart.getCreatedBy()).isEqualTo(user.getEmail());
        Assertions.assertThat(existingCart.getQuantity()).isEqualTo(9L);
        Assertions.assertThat(existingCart.getTotalAmount()).isEqualTo(900);

    }

    @Test
    public void filterProductsInCartOneByOneForCheckout(){

        Long id = 1L;

        User user = new User();
        user.setEmail(JwtAuthenticationFilter.CURRENT_USER);

        Inventory inventory = new Inventory();
        inventory.setInventoryId(1L);

        Cart existingCart = new Cart();
        existingCart.setInventory(inventory);
        existingCart.setCreatedBy(user.getEmail());
        existingCart.setFilter(false);

        when(userRepository.findById(user.getEmail())).thenReturn(Optional.of(user));
        when(inventoryRepository.findById(id)).thenReturn(Optional.of(inventory));
        when(cartRepository.findByInventory_InventoryIdAndUserEmail(id, user.getEmail())).thenReturn(Optional.of(existingCart));

        cartService.filterCart(id);

        Assertions.assertThat(existingCart.isFilter()).isEqualTo(true);
    }

    @Test
    public void deleteOneProductInCart(){

        Long id = 1L;
        String username = JwtAuthenticationFilter.CURRENT_USER;
        cartService.deleteOneProductInCart(id);

        verify(cartRepository, times(1)).deleteByInventory_InventoryIdAndUserEmail(id,username);

    }

    @Test
    public void deleteAllCustomersProductInCart(){

        String username = JwtAuthenticationFilter.CURRENT_USER;
        cartService.deleteAllProductsInCart();

        verify(cartRepository, times(1)).deleteAllByUserEmail(username);

    }


}