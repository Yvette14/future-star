package com.thoughtworks.api;

import com.thoughtworks.dto.User;
import com.thoughtworks.exception.InvalidCredentialException;
import com.thoughtworks.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String login(@RequestBody User user) {
        if (loginService.login(user)) {
            return "login successfully!";
        }
        throw new InvalidCredentialException("login failed!");
    }
}
