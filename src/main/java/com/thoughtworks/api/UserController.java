package com.thoughtworks.api;

import com.thoughtworks.dto.User;
import com.thoughtworks.exception.IllegalArgumentException;
import com.thoughtworks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        if (userService.createUser(user)) {
            return user;
        }
        throw new IllegalArgumentException("create user failed!");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUserAge(@PathVariable String username, @RequestBody User user) {
        if (userService.updateUserAge(username, user)) {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>("update age failed!", HttpStatus.FORBIDDEN);
    }

    @GetMapping(params = "age")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByAge(@RequestParam int age) {
        return userService.getUsersByAge(age);
    }
}
