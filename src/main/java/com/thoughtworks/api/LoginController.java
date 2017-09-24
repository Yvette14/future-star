package com.thoughtworks.api;

import com.thoughtworks.dto.Cache;
import com.thoughtworks.dto.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    Cache loginCache = new Cache();
    @PostMapping
    public String login(@RequestBody User user) {
        if (loginCache.login(user)) {
            return "login successfully!";
        }
        return "login failed!";
    }
}
