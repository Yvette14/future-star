package com.thoughtworks.api;

import com.thoughtworks.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @PostMapping
    public String login(@RequestBody User user) {
        if ("future_star".equals(user.getUsername()) && "12345678".equals(user.getPassword())) {
            return "login successfully!";
        }
        return "login failed!";
    }
}
