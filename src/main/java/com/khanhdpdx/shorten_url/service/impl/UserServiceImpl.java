package com.khanhdpdx.shorten_url.service.impl;

import com.khanhdpdx.shorten_url.dto.user.SignupRequest;
import com.khanhdpdx.shorten_url.entity.User;
import com.khanhdpdx.shorten_url.repository.UserRepository;
import com.khanhdpdx.shorten_url.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(SignupRequest signupRequest) {
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setCreationDate(new Date());
        user.setRoleName("ROLE_USER");
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        userRepository.save(user);
    }
}
