package com.khanhdpdx.shorten_url.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
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
}
