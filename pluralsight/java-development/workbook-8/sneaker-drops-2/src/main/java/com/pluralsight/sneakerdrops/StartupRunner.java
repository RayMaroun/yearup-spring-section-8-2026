package com.pluralsight.sneakerdrops;

import com.pluralsight.sneakerdrops.service.DropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final DropService dropService;

    @Autowired
    public StartupRunner(DropService dropService) {
        this.dropService = dropService;
    }

    @Override
    public void run(String... args) {
        System.out.println(dropService.getStatus());
    }
}
