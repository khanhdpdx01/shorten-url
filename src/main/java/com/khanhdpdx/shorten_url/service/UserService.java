package com.khanhdpdx.shorten_url.service;

import com.khanhdpdx.shorten_url.dto.user.SignupRequest;

public interface UserService {
    void save(SignupRequest signupRequest);
}
