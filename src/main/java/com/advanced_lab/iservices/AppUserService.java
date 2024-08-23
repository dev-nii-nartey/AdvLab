package com.advanced_lab.iservices;


import com.advanced_lab.dto.DtoUser;

import java.util.List;


public interface AppUserService {
    DtoUser create(DtoUser userObject);
    DtoUser update(Long id, DtoUser userObject);
    boolean delete(Long id);
    List<DtoUser> listAll();
    List<DtoUser> searchUser(String query);
    DtoUser findByEmail(String email);

}
