package com.thoughtworks.api;

import com.thoughtworks.dto.User;
import com.thoughtworks.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody User user) {
        if (loginService.login(user)) {
            return new ResponseEntity<>("login successfully!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("login failed!", HttpStatus.UNAUTHORIZED);
    }
}
