package com.khanhdpdx.shorten_url.controller;

import com.khanhdpdx.shorten_url.dto.ApiResponse;
import com.khanhdpdx.shorten_url.entity.User;
import com.khanhdpdx.shorten_url.repository.UserRepository;
import com.khanhdpdx.shorten_url.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest,
                                                       HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        jwtRequest.getUsernameOrEmail(),
                        jwtRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateJwtToken(authentication);
        Cookie cookie = new Cookie("access_token", token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 5);
        response.addCookie(cookie);

        return ResponseEntity.ok(new ApiResponse(true, "Success"));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setCreationDate(new Date());
        user.setRoleName("ROLE_USER");
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
    }
}
