package com.khanhdpdx.shorten_url.dto;

public class UrlDTO {
    private String originURL;

    public UrlDTO() {
    }

    public UrlDTO(String originURL) {
        this.originURL = originURL;
    }

    public String getOriginURL() {
        return originURL;
    }

    public void setOriginURL(String originURL) {
        this.originURL = originURL;
    }
}
