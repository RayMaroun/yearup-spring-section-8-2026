package com.pluralsight.sneakerdrops.service;

import org.springframework.stereotype.Service;

// Carried over from Module 1. Still a managed bean, but nothing calls it now.
// We replace it with a real SneakerService in Module 4.
@Service
public class DropService {

    public String getStatus() {
        return "Sneaker drops loading...";
    }
}
