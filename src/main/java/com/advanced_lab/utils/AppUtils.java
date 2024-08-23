package com.advanced_lab.utils;


import com.advanced_lab.dto.DtoUser;
import com.advanced_lab.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AppUtils {
    // Helper method to create ResponseEntity
    public static <T> ResponseEntity<T> createResponse(T body, HttpStatus status) {
        return new ResponseEntity<>(body, status);
    }

    //Helper method to convert to Dto
    public static DtoUser convertToDto(User user) {
        DtoUser savedUser = new DtoUser();
        savedUser.setEmail(user.getEmail());
        savedUser.setFirstName(user.getFirstName());
        savedUser.setLastName(user.getLastName());
//        savedUser.setRoles(user.getRoles().toString());
        savedUser.setEnabled(user.isEnabled());
        return savedUser;
    }
}
