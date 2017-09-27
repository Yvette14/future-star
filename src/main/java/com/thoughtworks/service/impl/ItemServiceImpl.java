package com.thoughtworks.service.impl;

import com.thoughtworks.entity.Item;
import com.thoughtworks.repository.ItemRepository;
import com.thoughtworks.service.ItemService;
import com.thoughtworks.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Transactional
    public Item createItem(Item item) {
        item.setId(StringUtils.randomUUID());
        return itemRepository.save(item);
    }
}
