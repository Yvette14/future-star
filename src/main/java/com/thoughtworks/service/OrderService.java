package com.thoughtworks.service;

import com.thoughtworks.entity.Item;

import java.util.List;

public interface OrderService {
    String createOrder(List<Item> items);
}
