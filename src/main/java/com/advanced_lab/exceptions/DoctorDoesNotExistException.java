package com.advanced_lab.exceptions;

public class DoctorDoesNotExistException extends RuntimeException {

    public DoctorDoesNotExistException(String message) {
        super(message);
    }

}
