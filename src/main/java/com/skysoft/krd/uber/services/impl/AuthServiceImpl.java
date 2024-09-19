package com.skysoft.krd.uber.services.impl;

import com.skysoft.krd.uber.dto.DriverDto;
import com.skysoft.krd.uber.dto.LoginResponseDto;
import com.skysoft.krd.uber.dto.SignupDto;
import com.skysoft.krd.uber.dto.UserDto;
import com.skysoft.krd.uber.entities.Driver;
import com.skysoft.krd.uber.entities.User;
import com.skysoft.krd.uber.entities.enums.Role;
import com.skysoft.krd.uber.exceptions.ResourceNotFoundException;
import com.skysoft.krd.uber.exceptions.RuntimeConflictException;
import com.skysoft.krd.uber.repositories.UserRepository;
import com.skysoft.krd.uber.security.JwtService;
import com.skysoft.krd.uber.services.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl  implements AuthService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public String[] login(String username, String password) {
       Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

       User user=(User)authentication.getPrincipal();
       String accessToken=jwtService.generateAccessToken(user);
       String refreshToken=jwtService.generateRefreshToken(user);

        return new String[]{accessToken,refreshToken};
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        User userDB=userRepository.findByEmail(signupDto.getEmail()).orElse(null);

        if(userDB !=null)
            throw new RuntimeConflictException("User already exists with email: done"+signupDto.getEmail());

        User user = modelMapper.map(signupDto, User.class);
        user.setRole(Set.of(Role.RIDER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        //creating user related entities

        riderService.createNewRider(savedUser);
        walletService.createNewWallet(savedUser);
        // TODO add wallet related service here

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onbordNewDriver(Long userId,String vehicleId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User does not exist with id: " + userId));
        if (user.getRole().contains(Role.DRIVER))
            throw  new RuntimeConflictException("User with ID "+userId+"already exists with role: " + user.getRole());

        Driver createdDriver=Driver.builder()
                .user(user)
                .vehicleId(vehicleId)
                .available(true)
                .build();
        user.getRole().add(Role.DRIVER);
        userRepository.save(user);
        Driver savedDriver=driverService.createNewDriver(createdDriver);

        return modelMapper.map(savedDriver, DriverDto.class);
    }

    @Override
    public String refreshToken(String refreshToken) {
        Long userId=jwtService.getUserIdFromToken(refreshToken);
        User user= userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User does not exist with id: " + userId));
        return jwtService.generateAccessToken(user);
    }
}
