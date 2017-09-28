package com.thoughtworks.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.cache.SessionCache;
import com.thoughtworks.dto.LoginBody;
import com.thoughtworks.entity.JWTUser;
import com.thoughtworks.entity.User;
import com.thoughtworks.repository.TokenAuthRepository;
import com.thoughtworks.repository.UserRepository;
import com.thoughtworks.service.LoginService;
import com.thoughtworks.service.UserService;
import com.thoughtworks.util.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class LoginControllerTest extends BaseControllerTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoginService loginService;

    @Autowired
    SessionCache sessionCache;

    @Autowired
    private TokenAuthRepository authRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    @BeforeEach
    void setUp() {
        User user = User.builder().id(StringUtils.randomUUID()).username("future_star").password("123456").age(22).build();
        userRepository.save(user);
    }

    @Test
    void should_login_successfully() throws Exception {
        LoginBody loginBody = LoginBody.builder().username("admin").password("123").build();

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword()));
        JWTUser principal = (JWTUser) authenticate.getPrincipal();

        Map payload = StringUtils.readJsonStringAsObject(StringUtils.writeObjectAsJsonString(principal), Map.class);

        String token = "Bearer " + authRepository.generateToken(payload);


        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginBody)))
                .andExpect(status().isCreated())
                .andExpect(header().string("authorization", token));

    }

    @Test
    void should_login_failed() throws Exception {
        LoginBody loginBody = LoginBody.builder().username("admin").password("wrong_password").build();

        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginBody)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$").value("Password is invalid!"));
    }
}
