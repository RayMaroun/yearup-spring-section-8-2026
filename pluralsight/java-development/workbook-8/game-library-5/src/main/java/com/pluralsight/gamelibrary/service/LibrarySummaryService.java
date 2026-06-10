package com.pluralsight.gamelibrary.service;

import org.springframework.stereotype.Service;

// Carried over from Module 1. Still a managed bean, but nothing calls it now.
// We replace it with a real GameService in Module 4.
@Service
public class LibrarySummaryService {

    public String buildSummary() {
        return "Game library ready. 0 games loaded (database coming next).";
    }
}
