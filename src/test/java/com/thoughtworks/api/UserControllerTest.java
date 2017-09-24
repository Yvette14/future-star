package com.thoughtworks.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.dto.Cache;
import com.thoughtworks.dto.User;
import com.thoughtworks.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends BaseControllerTest {

    @Autowired
    UserService userService;

    @Test
    void should_create_user() throws Exception{
        User user = User.builder().username("future_star").password("123456").age(22).build();

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("future_star"))
                .andExpect(jsonPath("$.password").value("123456"))
                .andExpect(jsonPath("$.age").value(22));
    }

    @Test
    void should_create_user_failed() throws Exception{
        User user = User.builder().username("").password("123456").age(22).build();

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isForbidden());
    }

    @Test
    void should_get_user_list() throws Exception {
        Cache.users.clear();
        User user = User.builder().username("future_star").password("123456").age(22).build();
        userService.createUser(user);

        mockMvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].username").value("future_star"))
                .andExpect(jsonPath("$[0].password").value("123456"))
                .andExpect(jsonPath("$[0].age").value(22));
    }

    
}
