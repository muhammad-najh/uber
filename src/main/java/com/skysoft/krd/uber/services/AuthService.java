package com.skysoft.krd.uber.services;

import com.skysoft.krd.uber.dto.DriverDto;
import com.skysoft.krd.uber.dto.SignupDto;
import com.skysoft.krd.uber.dto.UserDto;
import org.locationtech.jts.geom.Point;

public interface AuthService {
        String login(String username, String password);
        UserDto signup(SignupDto signupDto);
        DriverDto onbordNewDriver(Long userId);
}
