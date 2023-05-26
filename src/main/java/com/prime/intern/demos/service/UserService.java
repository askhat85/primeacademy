package com.prime.intern.demos.service;

import com.prime.intern.demos.model.UserInfo;


public interface UserService {
    public UserInfo create(UserInfo userInfo);
    public UserInfo getCurrentUser();
}
