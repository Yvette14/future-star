package com.thoughtworks.api;

import com.thoughtworks.dto.Cache;
import com.thoughtworks.dto.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    Cache userCache = new Cache();

    @PostMapping
    public String createUser(@RequestBody User user) {
        if(userCache.createUser(user)){
            return "create user successfully!";
        }
        return "create user failed!";
    }

    @GetMapping
    public List<User> getUsers() {
        return userCache.getUsers();
    }
}
