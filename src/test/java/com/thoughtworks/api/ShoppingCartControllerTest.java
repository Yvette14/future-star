package com.thoughtworks.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.entity.Item;
import com.thoughtworks.entity.User;
import com.thoughtworks.repository.ItemRepository;
import com.thoughtworks.repository.UserRepository;
import com.thoughtworks.util.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ShoppingCartControllerTest extends BaseControllerTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        User user = User.builder().id(StringUtils.randomUUID()).username("future_star").password("123456").age(22).build();
        userRepository.save(user);
    }

    @Test
    void should_add_item_to_shopping_cart() throws Exception {
        Item item = Item.builder().id(StringUtils.randomUUID()).itemName("tissue").price(3.0).build();
        itemRepository.save(item);

        mockMvc.perform(post("/api/shopping-cart/future_star")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(item)))
                .andExpect(status().isCreated());
    }
}
