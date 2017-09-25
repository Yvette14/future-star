package com.thoughtworks.api;

import com.thoughtworks.entity.Item;
import com.thoughtworks.repository.ItemRepository;
import com.thoughtworks.util.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ItemControllerTest extends BaseControllerTest {

    @Autowired
    ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        Item item1 = Item.builder().id(StringUtils.randomUUID()).itemName("cola").price(3.0).build();
        Item item2 = Item.builder().id(StringUtils.randomUUID()).itemName("water").price(2.0).build();
        Item item3 = Item.builder().id(StringUtils.randomUUID()).itemName("tissue").price(1.0).build();

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
    }

    @Test
    void should_return_all_items() throws Exception {

        mockMvc.perform(get("/api/items")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }
}
