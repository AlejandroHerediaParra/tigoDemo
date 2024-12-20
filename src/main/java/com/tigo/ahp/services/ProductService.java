package com.tigo.ahp.services;

import java.util.List;

import com.tigo.ahp.models.Product;

public interface ProductService {
  public List<Product> getAllProducts();
  public List<Product> searchProductsByTitle(String title);
  public Product getProductById(Long id);
}
