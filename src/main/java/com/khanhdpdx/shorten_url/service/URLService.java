package com.khanhdpdx.shorten_url.service;

import com.khanhdpdx.shorten_url.dto.ShortenURL;
import com.khanhdpdx.shorten_url.entity.URL;

import java.util.List;

public interface URLService {
    URL getOriginURL(String hash);

    List<URL> getURLs();

    ShortenURL shortenURL(String originURL);
}
