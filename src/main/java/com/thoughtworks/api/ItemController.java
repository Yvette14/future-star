package com.thoughtworks.api;

import com.thoughtworks.entity.Item;
import com.thoughtworks.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getAll() {
        return itemService.getAllItems();
    }
}
