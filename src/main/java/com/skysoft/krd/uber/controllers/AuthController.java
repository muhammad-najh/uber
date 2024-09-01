package com.skysoft.krd.uber.controllers;

import com.skysoft.krd.uber.dto.DriverDto;
import com.skysoft.krd.uber.dto.OnBoardDriverDto;
import com.skysoft.krd.uber.dto.SignupDto;
import com.skysoft.krd.uber.dto.UserDto;
import com.skysoft.krd.uber.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
   public ResponseEntity<UserDto> signup(@RequestBody SignupDto signupDto){
       return new ResponseEntity<>(authService.signup(signupDto), HttpStatus.CREATED);

   }

   @PostMapping("/onboardNewDriver/{userId}")
    public ResponseEntity<DriverDto> onboardNewDriver(@PathVariable Long userId,@RequestBody OnBoardDriverDto onBoardDriverDto){
        return new ResponseEntity<>(authService.onbordNewDriver(userId,onBoardDriverDto.getVehicleId()),HttpStatus.CREATED);

   }
}
