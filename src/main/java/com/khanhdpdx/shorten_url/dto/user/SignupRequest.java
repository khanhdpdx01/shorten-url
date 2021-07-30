package com.khanhdpdx.shorten_url.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@PasswordMatches
public class SignupRequest {
    @NotBlank(message = "Not blank")
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank(message = "Not blank")
    @Email
    private String email;
    @NotBlank(message = "Not blank")
    @Size(min = 8, max = 30)
    private String password;
    @NotBlank(message = "Not blank")
    @Size(min = 8, max = 30)
    private String confirmPassword;

    public SignupRequest(String username, String email, String password, String confirmPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
