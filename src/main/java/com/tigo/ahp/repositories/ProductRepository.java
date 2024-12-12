package com.tigo.ahp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tigo.ahp.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}