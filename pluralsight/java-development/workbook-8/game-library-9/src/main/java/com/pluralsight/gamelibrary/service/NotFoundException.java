package com.pluralsight.gamelibrary.service;

// Thrown by the service layer when a requested id does not exist. The caller
// decides how to present it (the menu prints it; next week a controller turns
// it into a 404 Not Found).
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
