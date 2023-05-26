package com.prime.intern.demos.controller;

import com.prime.intern.demos.model.UserInfo;
import com.prime.intern.demos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    @Autowired
    private UserService userService;
    @PostMapping("/users")
    public UserInfo create(@RequestBody UserInfo userInfo){
        return userService.create(userInfo);
    }

    @GetMapping("/users/me")
    public UserInfo getCurrentUser() {
        return userService.getCurrentUser();
    }
}
