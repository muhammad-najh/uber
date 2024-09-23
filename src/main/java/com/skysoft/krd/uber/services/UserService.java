package com.skysoft.krd.uber.services;

import com.skysoft.krd.uber.entities.User;
import com.skysoft.krd.uber.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with email : "+username));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                ()->new UsernameNotFoundException("User not found with id: " + id));
    }
}
