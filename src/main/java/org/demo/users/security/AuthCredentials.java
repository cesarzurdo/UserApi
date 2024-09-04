package org.demo.users.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AuthCredentials {
    @NotBlank(message = "email must not be blank")
    @Email(message = "email must be a well-formed email address")
    private String email;
    @NotBlank(message = "password must not be blank")
    private String password;
}
