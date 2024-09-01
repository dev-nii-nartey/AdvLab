package com.advanced_lab.utils;


import com.advanced_lab.models.Role;
import com.advanced_lab.repositories.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
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

        log.info("Data has been loaded!, Application is UP!!");
    }
}