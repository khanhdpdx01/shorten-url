package com.khanhdpdx.shorten_url.controller;

import com.khanhdpdx.shorten_url.dto.ApiResponse;
import com.khanhdpdx.shorten_url.dto.ShortenURL;
import com.khanhdpdx.shorten_url.entity.URL;
import com.khanhdpdx.shorten_url.exception.UrlNotFoundException;
import com.khanhdpdx.shorten_url.repository.URLRepository;
import com.khanhdpdx.shorten_url.security.UserDetailsImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class URLController {
    @Autowired
    private URLRepository urlRepository;

    @GetMapping("/")
    public String home(Model model) {
//        model.addAttribute("originURL", new UrlDTO());
        return "index";
    }

    @GetMapping("/{hash:[A-Za-z]+}")
    @ResponseBody
    public ResponseEntity<?> getOriginURL(@PathVariable("hash") String hash) throws URISyntaxException {
        URL url = urlRepository.getFirstByHash(hash);
        if(url == null) {
            /*return ResponseEntity.badRequest().body(new ApiResponse(false, "Url is invalid"));*/
            throw new UrlNotFoundException();
        }
        URI uri = new URI(url.getOriginURL());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/urls")
    @ResponseBody
    public List<URL> getURLs() {
        return urlRepository.findAll();
    }

    @PostMapping("/shorten-url")
    @ResponseBody
    public ShortenURL shortenURL(String originURL) {
        String hash = RandomStringUtils.randomAlphabetic(7);
        URL url = new URL(hash,
                originURL,
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis() + 60 * 60 * 24 * 365));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.getPrincipal() instanceof UserDetails) {
            url.setUserId(((UserDetailsImpl)authentication.getPrincipal()).getId());
        } else {
            url.setUserId(null);
        }

        urlRepository.save(url);
        return new ShortenURL(hash);
    }
}
