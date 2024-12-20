package com.tigo.ahp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tigo.ahp.models.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
