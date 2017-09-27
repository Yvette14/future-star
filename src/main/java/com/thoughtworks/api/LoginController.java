package com.thoughtworks.api;

import com.thoughtworks.entity.User;
import com.thoughtworks.exception.InvalidCredentialException;
import com.thoughtworks.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String login(@RequestBody User user) {
        if (loginService.isValid(user.getUsername(), user.getPassword())) {
            return "login successfully!";
        }
        throw new InvalidCredentialException("login failed!");
    }
}
