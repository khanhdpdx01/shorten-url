package com.khanhdpdx.shorten_url.repository;

import com.khanhdpdx.shorten_url.entity.URL;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface URLRepository extends MongoRepository<URL, String> {
    URL getFirstByHash(String hash);
}
