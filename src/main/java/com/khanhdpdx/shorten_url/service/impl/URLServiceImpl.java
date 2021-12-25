package com.khanhdpdx.shorten_url.service.impl;

import com.khanhdpdx.shorten_url.dto.ShortenURL;
import com.khanhdpdx.shorten_url.entity.URL;
import com.khanhdpdx.shorten_url.entity.User;
import com.khanhdpdx.shorten_url.exception.UrlNotFoundException;
import com.khanhdpdx.shorten_url.repository.URLRepository;
import com.khanhdpdx.shorten_url.security.UserDetailsImpl;
import com.khanhdpdx.shorten_url.service.URLService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Date;
import java.util.List;

@Service
public class URLServiceImpl implements URLService {
    @Autowired
    private URLRepository urlRepository;

    @Override
    public URL getOriginURL(String hash) {
        URL url = urlRepository.getFirstByHash(hash);
        if (url == null) {
            /*return ResponseEntity.badRequest().body(new ApiResponse(false, "Url is invalid"));*/
            throw new UrlNotFoundException();
        }
        return url;
    }

    @Override
    public List<URL> getURLs() {
        return urlRepository.findAll();
    }

    @Override
    public ShortenURL shortenURL(String originURL) {
        String hash = RandomStringUtils.randomAlphabetic(7);
        LocalDateTime localDateTime = LocalDateTime.now();

        URL url = new URL();
        url.setHash(hash)
                .setOriginURL(originURL)
                .setCreationDate(Timestamp.valueOf(localDateTime))
                .setExpirationDate(Timestamp.valueOf(localDateTime.plusMonths(12)));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UserDetails) {
            url.setUser(new User().setUserId(((UserDetailsImpl) authentication.getPrincipal()).getUserId()));
        } else {
            url.setUser(null);
        }

        urlRepository.save(url);
        return new ShortenURL(hash);
    }
}
