package com.thoughtworks.repository;

import com.thoughtworks.entity.ShoppingCart;
import com.thoughtworks.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String>{
    ShoppingCart findShoppingCartByUser(User user);
}
