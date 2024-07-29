package com.skysoft.krd.uber.services.impl;

import com.skysoft.krd.uber.dto.DriverDto;
import com.skysoft.krd.uber.dto.SignupDto;
import com.skysoft.krd.uber.dto.UserDto;
import com.skysoft.krd.uber.entities.Rider;
import com.skysoft.krd.uber.entities.User;
import com.skysoft.krd.uber.entities.enums.Role;
import com.skysoft.krd.uber.exceptions.RunTimeConflicException;
import com.skysoft.krd.uber.repositories.UserRepository;
import com.skysoft.krd.uber.services.AuthService;
import com.skysoft.krd.uber.services.RiderService;
import com.skysoft.krd.uber.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl  implements AuthService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    @Override
    public String login(String username, String password) {
        return "";
    }

    @Override
    public UserDto signup(SignupDto signupDto) {
        User userDB=userRepository.findByEmail(signupDto.getEmail()).orElse(null);

        if(userDB !=null)
            throw new RunTimeConflicException("User already exists with email: "+signupDto.getEmail());

        User user = modelMapper.map(signupDto, User.class);
        user.setRole(Set.of(Role.RIDER));
        User savedUser = userRepository.save(user);

        //creating user related entities

        Rider rider = riderService.createNewRider(savedUser);
        // TODO add wallet related service here

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onbordNewDriver(Long userId) {
        return null;
    }
}
