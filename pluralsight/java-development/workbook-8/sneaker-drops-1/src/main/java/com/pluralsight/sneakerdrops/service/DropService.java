package com.pluralsight.sneakerdrops.service;

import org.springframework.stereotype.Service;

// Module 1: our first bean, created and managed by Spring (@Service).
@Service
public class DropService {

    public String getStatus() {
        return "Sneaker drops loading...";
    }
}
