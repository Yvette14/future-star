package com.thoughtworks.service.impl;

import com.thoughtworks.entity.Item;
import com.thoughtworks.repository.ItemRepository;
import com.thoughtworks.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }
}
