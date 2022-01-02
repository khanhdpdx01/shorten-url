package com.khanhdpdx.shorten_url.service.impl;

import com.khanhdpdx.shorten_url.dto.ShortenURL;
import com.khanhdpdx.shorten_url.entity.URL;
import com.khanhdpdx.shorten_url.entity.User;
import com.khanhdpdx.shorten_url.exception.CustomSlugExistedException;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class URLServiceImpl implements URLService {
    @Autowired
    private URLRepository urlRepository;

    @Override
    public URL getOriginURL(String hash) {
        URL url = urlRepository.getFirstByHash(hash)
                .orElseThrow(() -> new UrlNotFoundException());
        return url;
    }

    @Override
    public List<URL> getURLs() {
        return urlRepository.findAll();
    }

    @Override
    public ShortenURL shortenURL(String originURL, String customSlug) {
        String hash = null;
        Optional<URL> checkUrl;

        /* Kiem tra URL da ton tai hay chua*/
        if(!customSlug.isBlank()) {
            checkUrl = urlRepository.getFirstByHash(customSlug);
            if (checkUrl.isPresent()) throw new CustomSlugExistedException();
            hash = customSlug;
        } else {
            do {
                hash = RandomStringUtils.randomAlphabetic(7);
                checkUrl = urlRepository.getFirstByHash(hash);
            } while(checkUrl.isPresent());
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        URL url = new URL();
        url.setHash(hash)
                .setOriginURL(originURL)
                .setCreationDate(Timestamp.valueOf(localDateTime))
                .setExpirationDate(Timestamp.valueOf(localDateTime.plusMonths(12)));

        /* Lấy userId từ SecurityContextHolder*/
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
