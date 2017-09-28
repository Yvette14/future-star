package com.thoughtworks.api;

import com.thoughtworks.entity.User;
import com.thoughtworks.exception.IllegalArgumentException;
import com.thoughtworks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Secured("ROLE_CREATE_USER")
    public String createUser(@RequestBody User user) {
        if (userService.createUser(user)) {
            return user.getUsername();
        }
        throw new IllegalArgumentException("create user failed!");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/{username}")
    @Secured("ROLE_UPDATE_USER")
    public User updateUserAge(@PathVariable String username, @RequestBody User user) {
        if (userService.updateUserAge(username, user)) {
            return user;
        }
        throw new IllegalArgumentException("update age failed!");
    }

    @GetMapping(params = "age")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByAge(@RequestParam int age) {
        return userService.getUsersByAge(age);
    }
}
