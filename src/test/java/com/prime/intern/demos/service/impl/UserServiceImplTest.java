package com.prime.intern.demos.service.impl;

import com.prime.intern.demos.model.UserInfo;
import com.prime.intern.demos.repository.UserInfoRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserInfoRepository userInfoRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void testCreate(){
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword("Qwerty123!");
        String encodedPassword = "UXdlcnR5MTIzIQ==";
        Mockito.doReturn(encodedPassword).when(passwordEncoder)
                .encode(userInfo.getPassword());
        userService.create(userInfo);
        Mockito.verify(userInfoRepository).save(userInfo);
        Assertions.assertEquals(encodedPassword, userInfo.getPassword());
    }
}
