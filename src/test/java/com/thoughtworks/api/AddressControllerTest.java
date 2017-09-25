package com.thoughtworks.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.entity.Address;
import com.thoughtworks.entity.User;
import com.thoughtworks.repository.UserRepository;
import com.thoughtworks.util.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AddressControllerTest extends BaseControllerTest{

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = User.builder().id(StringUtils.randomUUID()).username("new_user").password("123456").age(22).build();
        userRepository.save(user);
    }

    @Test
    void should_create_an_address() throws Exception {
        Address address = Address.builder().id(StringUtils.randomUUID()).description("street1").build();

        mockMvc.perform(post("/api/addresses/new_user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(address)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description").value("street1"));
    }
}