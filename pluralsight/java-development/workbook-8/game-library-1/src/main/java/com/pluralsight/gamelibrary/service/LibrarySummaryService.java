package com.pluralsight.gamelibrary.service;

import org.springframework.stereotype.Service;

// Module 1: our first bean. @Service tells Spring to manage one instance.
@Service
public class LibrarySummaryService {

    public String buildSummary() {
        return "Game library ready. 0 games loaded (database coming next).";
    }
}
