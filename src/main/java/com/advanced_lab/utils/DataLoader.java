package com.advanced_lab.utils;


import com.advanced_lab.models.Role;
import com.advanced_lab.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Autowired
    public DataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        Role adminRole = new Role("ADMIN", "Manages everything on the system");
        Role userRole = new Role("USER", "Ability to purchase stuff the system");


        roleRepository.saveAll(List.of(adminRole,userRole));

        System.out.println("Data has been loaded!");
    }
}