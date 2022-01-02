package com.khanhdpdx.shorten_url.repository;

import com.khanhdpdx.shorten_url.entity.URL;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface URLRepository extends JpaRepository<URL, String> {
    Optional<URL> getFirstByHash(String hash);
}
