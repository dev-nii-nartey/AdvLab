package com.advanced_lab.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class DtoUser {
    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;

    private Boolean enabled;

    @Size(min = 3, max = 15, message = "Username must be between 3 and 15 characters")
    @NotBlank(message = "FirstName is required")
    private String firstName;

    @NotBlank(message = "LastName is required")
    @Size(min = 3, max = 15, message = "Username must be between 3 and 15 characters")
    private String lastName;

    @NotBlank(message = "Password is required")
    private String password;

    private String roles;
}
