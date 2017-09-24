package com.thoughtworks.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class User {

    private String username;
    private String password;
    private int age;
//
//    public User() {
//    }
//
//    public User(String username, String password, int age) {
//        this.username = username;
//        this.password = password;
//        this.age = age;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public int getAge() {
//        return age;
//    }

}
