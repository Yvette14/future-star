package com.thoughtworks.service;

import com.thoughtworks.entity.ShoppingCart;

public interface ShoppingCartService {
    void addItem(String itemId);

    ShoppingCart getShoppingCart();
}
