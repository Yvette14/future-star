package com.thoughtworks.api;

import com.thoughtworks.entity.Item;
import com.thoughtworks.entity.ShoppingCart;
import com.thoughtworks.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(@RequestBody String itemId) {
        shoppingCartService.addItem(itemId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ShoppingCart getShoppingCart() {
        return shoppingCartService.getShoppingCart();
    }
}
