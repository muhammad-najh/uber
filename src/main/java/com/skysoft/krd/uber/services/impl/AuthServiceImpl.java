package com.skysoft.krd.uber.services.impl;

import com.skysoft.krd.uber.dto.DriverDto;
import com.skysoft.krd.uber.dto.SignupDto;
import com.skysoft.krd.uber.dto.UserDto;
import com.skysoft.krd.uber.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl  implements AuthService {
    @Override
    public String login(String username, String password) {
        return "";
    }

    @Override
    public UserDto signup(SignupDto signupDto) {
        return null;
    }

    @Override
    public DriverDto onbordNewDriver(Long userId) {
        return null;
    }
}
