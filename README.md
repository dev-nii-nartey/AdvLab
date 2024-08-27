# Secure Spring Boot Web Application

## Overview

This Spring Boot application demonstrates advanced Spring Boot Security concepts, implementing JWT (JSON Web Token) authentication, role-based authorization, and various security features. It's designed as a RESTful API with enhanced security measures.

## Features

- JWT-based authentication
- Role-based authorization (USER and ADMIN roles)
- Stateless session management
- CORS (Cross-Origin Resource Sharing) configuration
- Rate limiting
- HTTPS redirection

## Security Measures

### Authentication

- Uses JWT for stateless authentication
- Passwords are encoded using BCryptPasswordEncoder
- Custom JwtAuthenticationFilter for processing JWT tokens

### Authorization

- Role-based access control (RBAC) is implemented
- The /api/user endpoint is accessible only to users with ADMIN role
- The /api/auth/** endpoints are publicly accessible
- All other endpoints require authentication

### CORS Configuration

- Configured to allow specific origins, methods, and headers
- Supports credentials and exposes custom headers

### Rate Limiting

- Implements a custom RateLimitFilter to prevent abuse of the API

### HTTPS Redirection

- Configures the embedded Tomcat server to redirect HTTP requests to HTTPS
- Uses port 8443 for HTTPS and redirects from port 8080

## Setup and Running

1. Ensure you have Java and Maven installed on your system.
2. Clone this repository.
3. Navigate to the project directory.
4. Run the application using: `mvn spring-boot:run`
5. The API will be accessible at: `https://localhost:8443`

## API Endpoints

- POST `/api/auth/login`: Authenticate and receive a JWT
- GET `/api/user`: Access user data (requires ADMIN role)
- Other endpoints as implemented in your controllers

## Security Configuration

- CSRF protection is disabled for this API-based application
- Session management is set to STATELESS
- JWT authentication filter is added to the filter chain
- CORS is configured to allow specific origins and methods

## Future Improvements

- Implement refresh token mechanism
- Add more granular role-based access control
- Implement API documentation (e.g., using Swagger)
- Add comprehensive logging and auditing
- Implement secure password reset functionality

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is open source and available under the [MIT License](LICENSE).
