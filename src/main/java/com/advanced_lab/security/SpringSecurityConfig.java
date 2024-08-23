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
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

@Configuration
public class SpringSecurityConfig {

    // Bean for password encoding
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean for managing user details (in-memory user store)
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        // Create a user with USER role
        if (!userDetailsManager.userExists("nat")) {
            UserDetails nii = User.builder()
                    .username("nat")
                    .password(passwordEncoder().encode("pass"))
                    .roles("USER")
                    .build();
            userDetailsManager.createUser(nii);
        }

        // Create a user with ADMIN role
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

    // Bean for configuring the embedded Tomcat server
    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                // Configure security constraints to force HTTPS
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(redirectConnector());
        return tomcat;
    }

    // Helper method to create an HTTP connector that redirects to HTTPS
    private Connector redirectConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }

    // Bean for configuring Spring Security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/").permitAll()  // Allow access to home page for all
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // Restrict admin pages to ADMIN role
                        .anyRequest().authenticated()  // Require authentication for all other requests
                )
                .formLogin(form -> form
                        .successHandler(new CustomAuthenticationSuccessHandler())  // Custom success handler after login
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")  // Redirect to home page after logout
                )
                .requiresChannel(channel -> channel
                        .anyRequest().requiresSecure());  // Force HTTPS for all requests

        return http.build();
    }
}
