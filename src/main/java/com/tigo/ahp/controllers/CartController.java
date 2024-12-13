package com.tigo.ahp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tigo.ahp.dtos.CartItemDTO;
import com.tigo.ahp.dtos.CartItemRequest;
import com.tigo.ahp.models.Cart;
import com.tigo.ahp.models.CartItem;
import com.tigo.ahp.models.Product;
import com.tigo.ahp.models.User;
import com.tigo.ahp.services.CartService;
import com.tigo.ahp.services.ProductService;
import com.tigo.ahp.services.UserService;
import com.tigo.ahp.utils.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@Validated
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ModelMapper modelMapper;

    @CrossOrigin(origins = "*")
    @PostMapping("/users/cart/items")
    public ResponseEntity<Object> addCartItems(@RequestBody List<CartItemRequest> cartItemRequests, HttpServletRequest request) {
      try {
        String jwt = request.getHeader("Authorization").replace("Bearer ", ""); 
        String userEmail = jwtUtil.extractUsername(jwt); 

        User user = userService.getUserByEmail(userEmail);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Cart cart = cartService.getCartByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart = cartService.createCart(cart);
        }

        List<CartItem> cartItems = new ArrayList<>();
        for (CartItemRequest cartItemRequest : cartItemRequests) {
            Product product = productService.getProductById(cartItemRequest.getProductId());
            if (product == null) {
                continue;
            }

            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItems.add(cartItem);
        }

        List<CartItem> savedCartItems = cartService.addCartItems(cartItems); 

        List<CartItemDTO> cartItemDTOs = savedCartItems.stream()
                .map(item -> modelMapper.map(item, CartItemDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDTOs);
      } catch (Exception e) {
        return ResponseEntity.badRequest().body("invalid user token");
      } 
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/users/cart/items")
    public ResponseEntity<Object> getCartItems(HttpServletRequest request) {
      try {
        String jwt = request.getHeader("Authorization").replace("Bearer ", "");
        String userEmail = jwtUtil.extractUsername(jwt);

        User user = userService.getUserByEmail(userEmail);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<CartItem> cartItems = cartService.getCartItemsByUser(user);
        if (cartItems == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
        }

        List<CartItemDTO> cartItemDTOs = cartItems.stream()
                .map(item -> modelMapper.map(item, CartItemDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(cartItemDTOs);
      } catch (Exception e) {
        return ResponseEntity.badRequest().body("invalid user token");
      } 
    }
    

    @CrossOrigin(origins = "*")
    @DeleteMapping("/users/cart/items/{cartItemId}")
    public ResponseEntity<Object> deleteCartItem(@PathVariable Long cartItemId, HttpServletRequest request) {
      try {
        String jwt = request.getHeader("Authorization").replace("Bearer ", "");
        String userEmail = jwtUtil.extractUsername(jwt);

        User user = userService.getUserByEmail(userEmail);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        cartService.deleteCartItem(cartItemId); 

        return ResponseEntity.noContent().build();
      } catch (Exception e) {
        return ResponseEntity.badRequest().body("invalid user token");
      } 
    }
}
