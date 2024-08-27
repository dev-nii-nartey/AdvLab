package com.advanced_lab.controllers;



import com.advanced_lab.dto.DtoUser;
import com.advanced_lab.dto.JwtAuthResponse;
import com.advanced_lab.dto.LoginDto;
import com.advanced_lab.dto.RegistrationDto;
import com.advanced_lab.iservices.AuthService;
import com.advanced_lab.models.Role;
import com.advanced_lab.utils.AppUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;


    @PostMapping( "/signup")
    public ResponseEntity<String> signUp(@RequestBody RegistrationDto registrationDto) {
        // Create DtoUser using the builder pattern
        DtoUser body = DtoUser.builder()
                .firstName(registrationDto.getFirstName())
                .lastName(registrationDto.getLastName())
                .email(registrationDto.getEmail())
                .password(registrationDto.getPassword())
                .roles(registrationDto.getRole())
                .build();

        DtoUser newUser = authService.register(body);

        String responseMessage = String.format("User registered successfully: %s %s (%s) with role %s",
                newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), newUser.getRoles());
        return ResponseEntity.ok(responseMessage);
    };


    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
};
