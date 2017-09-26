package com.thoughtworks.service.impl;

import com.thoughtworks.entity.Item;
import com.thoughtworks.entity.ShoppingCart;
import com.thoughtworks.entity.User;
import com.thoughtworks.repository.ItemRepository;
import com.thoughtworks.repository.ShoppingCartRepository;
import com.thoughtworks.repository.UserRepository;
import com.thoughtworks.service.ShoppingCartService;
import com.thoughtworks.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Override
    public String addItem(String username, Item item) {
        ShoppingCart shoppingCart;
        User user = userRepository.findUserByUsername(username);
        if (shoppingCartRepository.findShoppingCartByUser(user) != null) {
            shoppingCart = shoppingCartRepository.findShoppingCartByUser(user);
            shoppingCart.getItems().add(item);
        }else {
            List<Item> items = new ArrayList<>();
            items.add(item);
            shoppingCart = ShoppingCart.builder().id(StringUtils.randomUUID()).user(user).items(items).build();
        }

        shoppingCartRepository.save(shoppingCart);

        return "Added Item To ShoppingCart.";
    }
}
