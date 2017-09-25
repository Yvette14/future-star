package com.thoughtworks.service;

import com.thoughtworks.entity.Item;

public interface ShoppingCartService {
    String addItem(String username, Item item);
}
