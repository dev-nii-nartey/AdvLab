package com.advanced_lab.iservices;


import com.advanced_lab.dto.DtoUser;
import com.advanced_lab.dto.LoginDto;

public interface AuthService {
    public DtoUser register(DtoUser userObject);
    String login(LoginDto loginDto);
}