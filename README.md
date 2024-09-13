# Book Management System

This is a Spring Boot application that provides a RESTful API for managing books and book recommendations.
I idea behind this project is to find and analyze memory management using tools like jprofiler or jvm, resolving the identified bottlenecks and analyzing again to check improvement .

## Project Structure

The project consists of the following main components:

1. `Application.java`: The main entry point for the Spring Boot application.
2. `BookController.java`: REST controller handling HTTP requests.
3. `IBookServiceImpl.java`: Service layer implementing business logic.
4. `Book.java`: Entity class representing a book.
5. `BookRepository.java`: JPA repository for database operations.

## API Endpoints

The application exposes the following API endpoints:

- `GET /api/books`: Retrieve all books
- `GET /api/books/recommendations/{genre}`: Get book recommendations by genre
- `POST /api/books`: Add a new book

## Running the Application

To run the application:

1. Ensure you have Java and Maven installed.
2. Navigate to the project root directory.
3. Run the following command:

