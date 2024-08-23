package com.advanced_lab.services;

import ch.qos.logback.classic.encoder.JsonEncoder;

import com.advanced_lab.dto.DtoUser;
import com.advanced_lab.dto.LoginDto;
import com.advanced_lab.exceptions.UserAlreadyExistsException;
import com.advanced_lab.iservices.AuthService;
import com.advanced_lab.models.Role;
import com.advanced_lab.models.User;
import com.advanced_lab.repositories.RoleRepository;
import com.advanced_lab.repositories.UserRepository;
import com.advanced_lab.security.JwtTokenProvider;
import com.advanced_lab.utils.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public DtoUser register(DtoUser userObject) {
        String email = userObject.getEmail();
        String firstName = userObject.getFirstName();
        String lastName = userObject.getLastName();
        String encodedPassword = passwordEncoder.encode(userObject.getPassword());
        String roles = userObject.getRoles();

        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }
        User newUser = new User(encodedPassword, email, firstName, lastName);
        User saved =   addRoleToUser(newUser, roles);
        return AppUtils.convertToDto(saved);
    }


    public User addRoleToUser(User user, String roleName) {
        Role role = roleRepository.findRoleByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.addRole(role);
        return userRepository.save(user);
    }


    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
;

        return jwtTokenProvider.generateToken(authentication);
    }
}
