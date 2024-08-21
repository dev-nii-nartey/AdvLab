# Secure Spring Boot Web Application

## Overview

This Spring Boot application demonstrates basic authentication and authorization features using Spring Security. It includes a secured page with role-based access control and a protected admin-only area.

## Features

- User authentication using Spring Security's default login form (Input Validations)
- Role-based authorization (USER and ADMIN roles)
- Secured page accessible to all authenticated users
- Admin-only area with additional access control
- Input and output Encoding
- Logout functionality

## Security Measures

### Authentication

- Uses Spring Security's form-based authentication
- Passwords are encoded using BCrypt, a strong hashing function
- Users are stored in-memory for demonstration purposes (In a production environment, this should be replaced with a database-backed user store)

### Authorization

- Role-based access control (RBAC) is implemented
- The `/authorized` endpoint is accessible to all authenticated users
- The "Authorized Button" on the `/authorized` page implements client-side and server-side role checks:
    - Client-side: JavaScript checks the user's role before attempting to access the admin area
    - Server-side: The `/authorized/admin` endpoint verifies the user's role again, ensuring that only users with the ADMIN role can access it

### CSRF Protection

- CSRF protection is disabled for demonstration purposes. In a production environment, CSRF protection should be enabled and properly configured.

### Secure Communication

- It's recommended to use HTTPS in production to encrypt all communication between the client and server.

## Setup and Running

1. Ensure you have Java and Maven installed on your system.
2. Clone this repository.
3. Navigate to the project directory.
4. Run the application using: `mvn spring-boot:run`
5. Access the application at: `http://localhost:8080`

## Usage

1. Access the login page at `/login` or by trying to access `/authorized`
2. Log in using one of the following credentials:
    - Regular user: username `nat`, password `pass`
    - Admin user: username `nii`, password `password`
3. After logging in, you'll be redirected to the `/authorized` page
4. Click the "Authorized Button":
    - If logged in as a regular user, you'll see an alert stating you're not authorized
    - If logged in as an admin, you'll be taken to the admin-only page

## Future Improvements

- Implement database-backed user management
- Add user registration functionality
- Enable and properly configure CSRF protection
- Implement more granular role-based access control
- Add logging to tackle potential Data
  Repudiation by Web
  Service and auditing of security events 
- Implement secure password reset functionality

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is open source and available under the [MIT License](LICENSE).