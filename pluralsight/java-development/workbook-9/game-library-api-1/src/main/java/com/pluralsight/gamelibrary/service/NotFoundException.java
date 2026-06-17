package com.pluralsight.gamelibrary.service;

// Thrown by the service when a requested id does not exist. In Week 10 the menu
// caught it; here it stays until Module 4, where the controller takes over the
// 404 (ResponseStatusException) and we delete this class.
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
