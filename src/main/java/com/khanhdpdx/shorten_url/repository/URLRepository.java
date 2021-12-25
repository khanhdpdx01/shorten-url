package com.khanhdpdx.shorten_url.repository;

import com.khanhdpdx.shorten_url.entity.URL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URLRepository extends JpaRepository<URL, String> {
    URL getFirstByHash(String hash);
}
