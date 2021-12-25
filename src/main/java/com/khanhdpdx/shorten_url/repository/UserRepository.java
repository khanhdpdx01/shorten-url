package com.khanhdpdx.shorten_url.repository;

import com.khanhdpdx.shorten_url.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
