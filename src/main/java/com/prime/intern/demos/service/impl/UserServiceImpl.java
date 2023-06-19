package com.prime.intern.demos.service.impl;

import com.prime.intern.demos.exception.InvalidUser;
import com.prime.intern.demos.model.UserInfo;
import com.prime.intern.demos.repository.UserInfoRepository;
import com.prime.intern.demos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public UserInfo create(UserInfo userInfo) {
        if(userInfo.getRoles()==null || userInfo.getLogin()==null) {
            throw new InvalidUser("Insufficient details to create user");
        }
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return userInfoRepository.save(userInfo);
    }
    @Override
    public UserInfo getCurrentUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return userInfoRepository.findByLogin(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("user not found " + userDetails.getUsername()));
    }
}
