package com.khanhdpdx.shorten_url.controller;

import com.khanhdpdx.shorten_url.dto.ShortenURL;
import com.khanhdpdx.shorten_url.entity.URL;
import com.khanhdpdx.shorten_url.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class URLController {
    @Autowired
    private URLService urlService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/{hash:[A-Za-z]+}")
    @ResponseBody
    public ResponseEntity<?> getOriginURL(@PathVariable("hash") String hash) throws URISyntaxException {
        URL url = urlService.getOriginURL(hash);
        URI uri = new URI(url.getOriginURL());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/urls")
    @ResponseBody
    public List<URL> getURLs() {
        return urlService.getURLs();
    }

    @PostMapping("/shorten-url")
    @ResponseBody
    public ShortenURL shortenURL(String originURL) {
        return urlService.shortenURL(originURL);
    }
}
