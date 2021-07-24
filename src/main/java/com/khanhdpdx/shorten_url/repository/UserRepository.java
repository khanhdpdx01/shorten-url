package com.khanhdpdx.shorten_url.repository;

import com.khanhdpdx.shorten_url.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {
    User findByUsername(String username);
}
