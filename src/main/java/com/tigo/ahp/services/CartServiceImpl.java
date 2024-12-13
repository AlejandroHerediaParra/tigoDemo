package com.tigo.ahp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigo.ahp.models.Cart;
import com.tigo.ahp.models.CartItem;
import com.tigo.ahp.models.User;
import com.tigo.ahp.repositories.CartItemRepository;
import com.tigo.ahp.repositories.CartRepository;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartByUser(User user) {
        return user.getCart();
    }

    @Override
    public List<CartItem> addCartItems(List<CartItem> cartItems) {
        List<CartItem> savedCartItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            savedCartItems.add(cartItemRepository.save(cartItem));
        }
        return savedCartItems;
    }

    // You can add more methods here, such as:
    // - removeCartItem(CartItem cartItem)
    // - updateCartItemQuantity(CartItem cartItem, int newQuantity)
    // - getCartById(Long cartId)
    // - etc.
}