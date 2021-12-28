package com.khanhdpdx.shorten_url.dto;

import static com.khanhdpdx.shorten_url.constant.AppConstant.domainName;

public class ShortenURL {
    private String shortenURL;

    public ShortenURL() {
    }

    public ShortenURL(String shortenURL) {
        this.shortenURL = domainName + ":3000/" + shortenURL;
    }

    public String getShortenURL() {
        return shortenURL;
    }

    public void setShortenURL(String shortenURL) {
        this.shortenURL = shortenURL;
    }
}
