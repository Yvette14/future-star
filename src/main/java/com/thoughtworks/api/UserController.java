package com.thoughtworks.api;

import com.thoughtworks.dto.User;
import com.thoughtworks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String createUser(@RequestBody User user) {
        if (userService.createUser(user)) {
            return "create user successfully!";
        }
        return "create user failed!";
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/{username}")
    public String updateUserAge(@PathVariable String username, @RequestBody User user) {
        if (userService.updateUserAge(username, user)) {
            return "update age successfully!";
        }
        return "update age failed!";
    }

    @GetMapping(params = "age")
    public List<User> findByAge(@RequestParam int age) {
        return userService.getUsersByAge(age);
    }
}
