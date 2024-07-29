package com.skysoft.krd.uber.controllers;

import com.skysoft.krd.uber.dto.SignupDto;
import com.skysoft.krd.uber.dto.UserDto;
import com.skysoft.krd.uber.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
   public UserDto signup(@RequestBody SignupDto signupDto){
       return authService.signup(signupDto);

   }
}
