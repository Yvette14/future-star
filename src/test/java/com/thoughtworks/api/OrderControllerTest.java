package com.thoughtworks.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.cache.SessionCache;
import com.thoughtworks.entity.Address;
import com.thoughtworks.entity.Item;
import com.thoughtworks.entity.User;
import com.thoughtworks.repository.AddressRepository;
import com.thoughtworks.repository.ItemRepository;
import com.thoughtworks.repository.ShoppingCartRepository;
import com.thoughtworks.repository.UserRepository;
import com.thoughtworks.service.OrderService;
import com.thoughtworks.service.ShoppingCartService;
import com.thoughtworks.util.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    OrderService orderService;

    @BeforeEach
    void setUp() {
        sessionCache.setCurrentUser(null);
    }

    @Test
    void should_create_order() throws Exception {
        List<Address> addresses = new ArrayList<>();
        Address address = Address.builder().id(StringUtils.randomUUID()).description("street1").build();
        addresses.add(address);
        addressRepository.save(address);

        User user = User.builder().id(StringUtils.randomUUID()).orders(Collections.EMPTY_LIST).addresses(addresses).username("future_star").password("123456").age(22).build();
        userRepository.save(user);

        Item item1 = Item.builder().id(StringUtils.randomUUID()).itemName("cola").price(3.0).build();
        Item item2 = Item.builder().id(StringUtils.randomUUID()).itemName("water").price(2.0).build();
        Item item3 = Item.builder().id(StringUtils.randomUUID()).itemName("tissue").price(1.0).build();

        itemRepository.save(Arrays.asList(item1,item2,item3));

        shoppingCartService.addItem(item1.getId());
        shoppingCartService.addItem(item2.getId());
        shoppingCartService.addItem(item3.getId());

        List<Item> items = shoppingCartRepository.findShoppingCartByUser(user).getItems();

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(items)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value("created order!"));

        assertThat(shoppingCartRepository.findShoppingCartByUser(user).getItems().size(), is(0));
    }

    @Test
    void should_return_user_order() throws Exception {
        List<Address> addresses = new ArrayList<>();
        Address address = Address.builder().id(StringUtils.randomUUID()).description("street1").build();
        addresses.add(address);
        addressRepository.save(address);

        User user = User.builder().id(StringUtils.randomUUID()).orders(Collections.EMPTY_LIST).addresses(addresses).username("future_star").password("123456").age(22).build();
        userRepository.save(user);

        Item item1 = Item.builder().id(StringUtils.randomUUID()).itemName("cola").price(3.0).build();
        Item item2 = Item.builder().id(StringUtils.randomUUID()).itemName("water").price(2.0).build();
        Item item3 = Item.builder().id(StringUtils.randomUUID()).itemName("tissue").price(1.0).build();

        itemRepository.save(Arrays.asList(item1,item2,item3));

        shoppingCartService.addItem(item1.getId());
        shoppingCartService.addItem(item2.getId());
        shoppingCartService.addItem(item3.getId());

        List<Item> items = shoppingCartRepository.findShoppingCartByUser(user).getItems();

        orderService.createOrder(items);

        mockMvc.perform(get("/api/orders")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
