package com.thoughtworks.service.impl;

import com.thoughtworks.cache.SessionCache;
import com.thoughtworks.entity.Item;
import com.thoughtworks.entity.Order;
import com.thoughtworks.entity.ShoppingCart;
import com.thoughtworks.entity.User;
import com.thoughtworks.repository.OrderRepository;
import com.thoughtworks.repository.ShoppingCartRepository;
import com.thoughtworks.repository.UserRepository;
import com.thoughtworks.service.OrderService;
import com.thoughtworks.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    SessionCache sessionCache;

    @Override
    public Order createOrder(List<Item> items) {
        User user = sessionCache.loadCurrentUser();

        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser(user);

        double totalPrice = 0;
        for (Item item : items) {
            totalPrice += item.getPrice();
        }
        items.clear();
        shoppingCart.setItems(items);
        shoppingCartRepository.save(shoppingCart);

        Order order = Order.builder().id(StringUtils.randomUUID()).totalPrice(totalPrice).address(user.getAddresses().get(0).getDescription()).items(items).build();
        orderRepository.save(order);
        user.getOrders().add(order);
        userRepository.save(user);

        return order;
    }
}