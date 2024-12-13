package com.tigo.ahp.services;

import java.util.List;

import com.tigo.ahp.models.Cart;
import com.tigo.ahp.models.CartItem;
import com.tigo.ahp.models.User;

public interface CartService {
  public Cart createCart(Cart cart);
  public Cart getCartByUser(User user);
  public List<CartItem> addCartItems(List<CartItem> cartItems);
}
