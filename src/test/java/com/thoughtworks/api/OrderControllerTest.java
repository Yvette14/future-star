package com.thoughtworks.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.cache.SessionCache;
import com.thoughtworks.entity.Address;
import com.thoughtworks.entity.Item;
import com.thoughtworks.entity.User;
import com.thoughtworks.repository.AddressRepository;
import com.thoughtworks.repository.ItemRepository;
import com.thoughtworks.repository.UserRepository;
import com.thoughtworks.util.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest extends BaseControllerTest {

    @Autowired
    SessionCache sessionCache;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    AddressRepository addressRepository;

    @BeforeEach
    void setUp() {
        sessionCache.setCurrentUser(null);
        List<Address> addresses = new ArrayList<>();
        Address address = Address.builder().id(StringUtils.randomUUID()).description("street1").build();
        addresses.add(address);
        addressRepository.save(address);
        User user = User.builder().id(StringUtils.randomUUID()).orders(Collections.EMPTY_LIST).addresses(addresses).username("future_star").password("123456").age(22).build();
        userRepository.save(user);

    }

    @Test
    void should_create_order() throws Exception {
        Item item1 = Item.builder().id(StringUtils.randomUUID()).itemName("cola").price(3.0).build();
        Item item2 = Item.builder().id(StringUtils.randomUUID()).itemName("water").price(2.0).build();
        Item item3 = Item.builder().id(StringUtils.randomUUID()).itemName("tissue").price(1.0).build();

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);

        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(items)))
                .andExpect(status().isCreated());

    }
}
