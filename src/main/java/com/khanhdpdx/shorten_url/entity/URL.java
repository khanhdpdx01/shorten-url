package com.khanhdpdx.shorten_url.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collation = "url")
public class URL {
    private String hash;
    private String originURL;
    private Date creationDate;
    private Date expirationDate;

    public URL() {
    }

    public URL(String hash, String originURL, Date creationDate, Date expirationDate) {
        this.hash = hash;
        this.originURL = originURL;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getOriginURL() {
        return originURL;
    }

    public void setOriginURL(String originURL) {
        this.originURL = originURL;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
