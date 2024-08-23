package com.advanced_lab.controllers;



import com.advanced_lab.dto.DtoUser;
import com.advanced_lab.dto.JwtAuthResponse;
import com.advanced_lab.dto.LoginDto;
import com.advanced_lab.iservices.AuthService;
import com.advanced_lab.models.Role;
import com.advanced_lab.utils.AppUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

//Build Register REST API
    @PostMapping("/signup")
    public ResponseEntity<?> signup( @Valid @RequestBody DtoUser body){
       DtoUser newUser = authService.register(body);
        return AppUtils.createResponse(newUser
                , HttpStatus.CREATED);
    };



    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
}
