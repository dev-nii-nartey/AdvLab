package com.advanced_lab.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Set;

// This class implements AuthenticationSuccessHandler to customize the behavior after a successful authentication
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Extract the roles (authorities) from the authenticated user
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        // Check if the user has the ROLE_ADMIN authority
        if (roles.contains("ROLE_ADMIN")) {
            // If the user is an admin, redirect to the admin welcome page
            response.sendRedirect("/admin/welcome");
        } else {
            // If the user is not an admin, redirect to the user welcome page
            response.sendRedirect("/user/welcome");
        }
    }
}
