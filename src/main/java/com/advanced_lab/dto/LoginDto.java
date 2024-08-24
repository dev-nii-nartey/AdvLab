package com.advanced_lab.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.AccessType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @Email(message = "Email is not a valid email")
    @NotBlank(message = "Email field is required")
    private String email;

    @Setter(lombok.AccessLevel.NONE)
    @NotBlank(message = "Password field is required")
    private String password;
}

