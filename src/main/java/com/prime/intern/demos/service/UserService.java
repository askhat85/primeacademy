package com.example.demo.service;

import com.example.demo.model.UserInfo;


public interface UserService {
    public UserInfo create(UserInfo userInfo);
    public UserInfo getCurrentUser();
}
