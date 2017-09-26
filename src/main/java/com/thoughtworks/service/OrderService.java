package com.thoughtworks.service;

import com.thoughtworks.entity.Item;
import com.thoughtworks.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(List<Item> items);

    List<Order> getOrders();
}
