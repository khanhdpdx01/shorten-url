package com.khanhdpdx.shorten_url.dto;

public class ShortenURL {
    private String shortenURL;

    public ShortenURL() {
    }

    public ShortenURL(String shortenURL) {
        this.shortenURL = "http://localhost:8080/" + shortenURL;
    }

    public String getShortenURL() {
        return shortenURL;
    }

    public void setShortenURL(String shortenURL) {
        this.shortenURL = shortenURL;
    }
}
