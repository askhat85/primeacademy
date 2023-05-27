package com.prime.intern.demos.service.impl;

import com.prime.intern.demos.model.UserInfo;
import com.prime.intern.demos.repository.UserInfoRepository;
import com.prime.intern.demos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserInfo create(UserInfo userInfo) {
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
