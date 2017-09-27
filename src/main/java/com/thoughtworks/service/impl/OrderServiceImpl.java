package com.thoughtworks.service.impl;

import com.thoughtworks.cache.SessionCache;
import com.thoughtworks.entity.Item;
import com.thoughtworks.entity.Order;
import com.thoughtworks.entity.ShoppingCart;
import com.thoughtworks.entity.User;
import com.thoughtworks.repository.ItemRepository;
import com.thoughtworks.repository.OrderRepository;
import com.thoughtworks.repository.ShoppingCartRepository;
import com.thoughtworks.repository.UserRepository;
import com.thoughtworks.service.OrderService;
import com.thoughtworks.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    SessionCache sessionCache;

    @Override
    @Transactional
    public Order createOrder(List<String> itemIds) {
        User user = sessionCache.loadCurrentUser();

        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser(user);

        List<Item> items = new ArrayList<>();
        double totalPrice = 0;
        for (String id : itemIds) {
            Item item = itemRepository.findOne(id);
            items.add(item);
            totalPrice += item.getPrice();
        }

        Order order = Order.builder().id(StringUtils.randomUUID()).totalPrice(totalPrice).address(user.getAddresses().get(0).getDescription()).items(items).build();
        orderRepository.save(order);

        user.getOrders().add(order);
        userRepository.save(user);

        List<Item> shoppingCartItems = shoppingCart.getItems();
        List<Item> leftShoppingCartItems = new ArrayList<>();

        shoppingCartItems.forEach(shoppingCartItem -> {
            if (!items.contains(shoppingCartItem)) {
                leftShoppingCartItems.add(shoppingCartItem);
            }
        });

        shoppingCart.setItems(leftShoppingCartItems);
        shoppingCartRepository.save(shoppingCart);

        return order;
    }

    @Override
    public List<Order> getOrders() {
        User user = sessionCache.loadCurrentUser();

        return userRepository.findUserByUsername(user.getUsername()).getOrders();
    }
}
