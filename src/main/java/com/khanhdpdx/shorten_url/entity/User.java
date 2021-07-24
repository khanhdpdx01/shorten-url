package com.khanhdpdx.shorten_url.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "role")
public class User {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String roleName;
    private Date creationDate;
    private Date updateDate;

    public User() {
    }

    public User(Long userId, String username, String password, String email, String roleName, Date creationDate, Date updateDate) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roleName = roleName;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
