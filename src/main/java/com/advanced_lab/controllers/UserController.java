package com.advanced_lab.controllers;



import com.advanced_lab.dto.DtoUser;
import com.advanced_lab.services.UserServiceImpl;
import com.advanced_lab.utils.AppUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api  ")
public class UserController {
    private final UserServiceImpl appUserService;

    public UserController(UserServiceImpl appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> signup( @RequestParam String name){
        System.out.println("here"+ name);
        List<DtoUser> found = appUserService.searchUser(name);
//        return AppUtils.createResponse(found
//                , HttpStatus.OK);
        return new ResponseEntity<>("Goalll", HttpStatus.OK);

    };

    @GetMapping("/login-success")
    public ResponseEntity<String> loginSuccess(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
        return ResponseEntity.ok("Login successful for user: " + principal.getName());
    }
}
