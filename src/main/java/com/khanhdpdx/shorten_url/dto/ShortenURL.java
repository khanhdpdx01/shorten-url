package com.khanhdpdx.shorten_url.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import static com.khanhdpdx.shorten_url.constant.AppConstant.domainName;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ShortenURL {
    private String shortenURL;

    public ShortenURL(String shortenURL) {
        this.shortenURL = domainName + "/" + shortenURL;
    }
}
