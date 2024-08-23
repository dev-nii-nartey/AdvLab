package com.advanced_lab.controllers;



import com.advanced_lab.dto.DtoUser;
import com.advanced_lab.services.UserServiceImpl;
import com.advanced_lab.utils.AppUtils;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/  ")
public class UserController {

    private final UserServiceImpl appUserService;

    public UserController(UserServiceImpl appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> signup( @RequestParam String name){
        List<DtoUser> found = appUserService.searchUser(name);
        return AppUtils.createResponse(found
                , HttpStatus.OK);

    };

//    @PostMapping("/signup")
//    public ResponseEntity<String> signup( @Valid @RequestBody DtoUser body){
//        System.out.println(body);
//        appUserService.create(body);
//        return AppUtils.createResponse("User successfully registered", HttpStatus.CREATED);
//
//    };


}
