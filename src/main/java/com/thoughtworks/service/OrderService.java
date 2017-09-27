package com.thoughtworks.service;

import com.thoughtworks.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(List<String> itemIds);

    List<Order> getOrders();
}
