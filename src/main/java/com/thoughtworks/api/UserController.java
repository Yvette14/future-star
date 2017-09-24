package com.thoughtworks.api;

import com.thoughtworks.dto.User;
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
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if(userService.createUser(user)){
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("create user failed!",HttpStatus.FORBIDDEN);
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
