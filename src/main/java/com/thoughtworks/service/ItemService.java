package com.thoughtworks.service;

import com.thoughtworks.entity.Item;

import java.util.List;

public interface ItemService {

    List<Item> getAllItems();

    Item createItem(Item item);
}
