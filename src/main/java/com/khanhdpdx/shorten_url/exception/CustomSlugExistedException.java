package com.khanhdpdx.shorten_url.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomSlugExistedException extends RuntimeException {
    public CustomSlugExistedException() {
        super("Custom slug is existed");
    }
}
