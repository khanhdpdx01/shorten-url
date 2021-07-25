package com.khanhdpdx.shorten_url.controller;

import com.khanhdpdx.shorten_url.dto.ShortenURL;
import com.khanhdpdx.shorten_url.entity.URL;
import com.khanhdpdx.shorten_url.repository.URLRepository;
import com.khanhdpdx.shorten_url.security.UserDetailsImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

@Controller
public class URLController {
    @Autowired
    private URLRepository urlRepository;

    @GetMapping("/")
    public String home(Model model) {
//        model.addAttribute("originURL", new UrlDTO());
        return "index";
    }

    @GetMapping("/{hash}")
    @ResponseBody
    public ResponseEntity<Object> getOriginURL(@PathVariable("hash") String hash) throws URISyntaxException {
        String originURL = urlRepository.getFirstByHash(hash).getOriginURL();
        URI uri = new URI(originURL);
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
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        urlRepository.save(new URL(hash,
                originURL,
                new Date(),
                new Date(new Date().getTime() + 60 * 60 * 24 * 365),
                1L));
        return new ShortenURL(hash);
    }
}
