package com.khanhdpdx.shorten_url.exception;

public class UrlNotFoundException extends RuntimeException{
    public UrlNotFoundException() {
        super("Url not found");
    }
}
