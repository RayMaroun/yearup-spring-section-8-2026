package com.pluralsight.gamelibrary;

import com.pluralsight.gamelibrary.data.GenreRepository;
import com.pluralsight.gamelibrary.models.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final GenreRepository genreRepository;

    @Autowired
    public StartupRunner(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public void run(String... args) {
        seedData();
        for (Genre g : genreRepository.findAll()) {
            System.out.println(g.getId() + " - " + g.getName());
        }
    }

    // Puts a little starting data in the database, but only when it is empty,
    // so re-running the app never creates duplicates. We grow this method as the
    // application grows.
    private void seedData() {
        if (genreRepository.count() == 0) {
            genreRepository.save(new Genre("Action"));
            genreRepository.save(new Genre("RPG"));
            genreRepository.save(new Genre("Indie"));
            genreRepository.save(new Genre("Shooter"));
            genreRepository.save(new Genre("Adventure"));
        }
    }
}
