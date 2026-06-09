package com.pluralsight.gamelibrary;

import com.pluralsight.gamelibrary.service.LibrarySummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final LibrarySummaryService librarySummaryService;

    @Autowired
    public StartupRunner(LibrarySummaryService librarySummaryService) {
        this.librarySummaryService = librarySummaryService;
    }

    @Override
    public void run(String... args) {
        System.out.println(librarySummaryService.buildSummary());
    }
}
