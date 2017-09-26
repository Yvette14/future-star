package com.thoughtworks.service;

import com.thoughtworks.entity.Item;
import com.thoughtworks.entity.ShoppingCart;

public interface ShoppingCartService {
    String addItem(Item item);

    ShoppingCart getShoppingCart();
}
