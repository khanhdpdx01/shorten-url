package com.khanhdpdx.shorten_url.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UrlNotFoundException extends RuntimeException{
    public UrlNotFoundException() {
        super("Url not found");
    }
}
