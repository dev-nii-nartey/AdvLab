package com.advanced_lab.services;


import com.advanced_lab.dto.DtoUser;
import com.advanced_lab.exceptions.UserAlreadyExistsException;
import com.advanced_lab.exceptions.UserNotFoundException;
import com.advanced_lab.iservices.AppUserService;
import com.advanced_lab.models.Role;
import com.advanced_lab.models.User;
import com.advanced_lab.repositories.RoleRepository;
import com.advanced_lab.repositories.UserRepository;
import com.advanced_lab.utils.AppUtils;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements AppUserService, UserDetailsService {

    RoleRepository roleRepository;
    UserRepository userRepository;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public DtoUser create(DtoUser userObject) {
        String email = userObject.getEmail();
        String firstName = userObject.getFirstName();
        String lastName = userObject.getLastName();
        String password = userObject.getPassword();
        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }
        User newUser = new User(password, email, firstName, lastName);
        User saved = userRepository.save(newUser);
        return AppUtils.convertToDto(saved);
    }

    @Transactional
    public void addRoleToUser(User user, String roleName) {
        Role role = roleRepository.findRoleByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.addRole(role);
        userRepository.save(user);
    }


    @Override
    @Transactional
    public DtoUser update(Long id, DtoUser userObject) {
            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

            existingUser.setFirstName(userObject.getFirstName());
            existingUser.setLastName(userObject.getLastName());
            existingUser.setPassword(userObject.getPassword());

            User updatedUser = userRepository.save(existingUser);
            return AppUtils.convertToDto(updatedUser);
        }


    @Override
    @Transactional
    public boolean delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        user.setDeleted(true);
        userRepository.save(user);
        return true;
    }

    @Override
    public List<DtoUser> listAll() {
        return userRepository.findAllActiveUsers().stream()
                .map(AppUtils::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DtoUser> searchUser(String query) {
        List<User> users = userRepository.searchByQuery(query); // Assuming this method is implemented
        return users.stream()
                .map(AppUtils::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DtoUser findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .filter(u -> !u.isDeleted())
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        return AppUtils.convertToDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

}
