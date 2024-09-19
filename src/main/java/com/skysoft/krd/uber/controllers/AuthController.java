package com.skysoft.krd.uber.controllers;

import com.skysoft.krd.uber.dto.*;
import com.skysoft.krd.uber.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
   public ResponseEntity<UserDto> signup(@RequestBody SignupDto signupDto){
       return new ResponseEntity<>(authService.signup(signupDto), HttpStatus.CREATED);

   }
   @Secured("ROLE_ADMIN")
   @PostMapping("/onboardNewDriver/{userId}")
    public ResponseEntity<DriverDto> onboardNewDriver(@PathVariable Long userId,@RequestBody OnBoardDriverDto onBoardDriverDto){
        return new ResponseEntity<>(authService.onbordNewDriver(userId,onBoardDriverDto.getVehicleId()),HttpStatus.CREATED);
   }

   @PostMapping("/login")
    ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpRequestHandlerServlet httpRequestHandlerServlet, HttpServletResponse httpServletResponse){
        String[] tokens = authService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());
        Cookie cookie=new Cookie("refreshToken",tokens[1]);
        cookie.setSecure(false); // false will be used for http , true is for https we can put in env variable or application property
        cookie.setHttpOnly(true); // java script won't access this token in cookie , keep it from xss attack
        httpServletResponse.addCookie(cookie);
        return ResponseEntity.ok(new LoginResponseDto(tokens[0]));
   }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request){
        String refreshToken= Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()-> new AuthenticationServiceException("Refresh Token not found inside cookies"));

        String accessToken=authService.refreshToken(refreshToken);
        return ResponseEntity.ok(new LoginResponseDto(accessToken));
    }
}
