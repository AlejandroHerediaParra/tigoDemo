package com.tigo.ahp.dtos;

import lombok.Data;

@Data
public class CartItemRequest {
  private Long productId;
  private int quantity;
}