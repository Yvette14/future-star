package com.thoughtworks.api;

import com.thoughtworks.entity.Order;
import com.thoughtworks.exception.IllegalArgumentException;
import com.thoughtworks.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrder(@RequestBody List<String> itemIds) {
        if (orderService.createOrder(itemIds) != null) {
            return "created order!";
        }
        throw new IllegalArgumentException("created order failed!");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrders() {
        return orderService.getOrders();
    }
}
