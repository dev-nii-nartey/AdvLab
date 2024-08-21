package com.advanced_lab.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        // Check if the "Nii" user already exists
        if (!userDetailsManager.userExists("nat")) {
            UserDetails nii = User.builder()
                    .username("nat")
                    .password(passwordEncoder().encode("pass"))
                    .roles("USER")
                    .build();
            userDetailsManager.createUser(nii);
        }

        // Check if the "nii" user already exists
        if (!userDetailsManager.userExists("nii")) {
            UserDetails admin = User.builder()
                    .username("nii")
                    .password(passwordEncoder().encode("password"))
                    .roles("ADMIN")
                    .build();
            userDetailsManager.createUser(admin);
        }

        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsService userDetailsService) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers("/", "/login").permitAll()
                            .requestMatchers("/authorized").authenticated()
                            .anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .defaultSuccessUrl("/authorized", true)
                )
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/"));
        return http.build();
    }
}