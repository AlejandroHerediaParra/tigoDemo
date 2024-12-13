package com.tigo.ahp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigo.ahp.models.Product;
import com.tigo.ahp.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
  @Autowired
  private ProductRepository productRepository;

  @Override
  public List<Product> getAllProducts() {
      return productRepository.findAll();
  }

  @Override
  public List<Product> searchProductsByTitle(String title) {
    return productRepository.findByTitleContainingIgnoreCase(title); 
  }

  @Override
  public Product getProductById(Long id) {
    return productRepository.findById(id).get();
  };
}
