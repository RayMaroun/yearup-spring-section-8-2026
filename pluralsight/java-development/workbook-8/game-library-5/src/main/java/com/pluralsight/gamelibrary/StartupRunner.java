package com.pluralsight.gamelibrary;

import com.pluralsight.gamelibrary.data.GameRepository;
import com.pluralsight.gamelibrary.data.GenreRepository;
import com.pluralsight.gamelibrary.models.Game;
import com.pluralsight.gamelibrary.models.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class StartupRunner implements CommandLineRunner {

    private final GameRepository gameRepository;
    private final GenreRepository genreRepository;   // also injected, so we can seed data

    @Autowired
    public StartupRunner(GameRepository gameRepository, GenreRepository genreRepository) {
        this.gameRepository = gameRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public void run(String... args) {
        seedData();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n=== Game Library ===");
            System.out.println("1) List all games");
            System.out.println("2) Find by release year");
            System.out.println("3) Search by title");
            System.out.println("4) Find by minimum rating");
            System.out.println("0) Quit");
            System.out.print("Choose: ");

            switch (scanner.nextInt()) {
                case 1 -> listGames();
                case 2 -> findByYear(scanner);
                case 3 -> findByTitle(scanner);
                case 4 -> findByRating(scanner);
                case 0 -> running = false;
                default -> System.out.println("Unknown option.");
            }
        }
    }

    private void listGames() {
        System.out.println("You have " + gameRepository.count() + " games:");
        for (Game g : gameRepository.findAll()) {
            System.out.println(g.getId() + " - " + g.getTitle() + " (" + g.getRating() + ")");
        }
    }

    private void findByYear(Scanner scanner) {
        System.out.print("Year: ");
        int year = scanner.nextInt();
        for (Game g : gameRepository.findByReleaseYear(year)) {
            System.out.println(g.getTitle() + " (" + g.getReleaseYear() + ")");
        }
    }

    private void findByTitle(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Title contains: ");
        String text = scanner.nextLine();
        for (Game g : gameRepository.findByTitleContaining(text)) {
            System.out.println(g.getTitle());
        }
    }

    private void findByRating(Scanner scanner) {
        System.out.print("Minimum rating: ");
        double min = scanner.nextDouble();
        for (Game g : gameRepository.findByRatingGreaterThan(min)) {
            System.out.println(g.getTitle() + " (" + g.getRating() + ")");
        }
    }

    // Seeds starting data when the database is empty (idempotent). Genres and
    // games are seeded independently; games have no genre until Module 4.
    private void seedData() {
        if (genreRepository.count() == 0) {
            genreRepository.save(new Genre("Action"));
            genreRepository.save(new Genre("RPG"));
            genreRepository.save(new Genre("Indie"));
            genreRepository.save(new Genre("Shooter"));
            genreRepository.save(new Genre("Adventure"));
        }
        if (gameRepository.count() == 0) {
            gameRepository.save(new Game("Hollow Knight", 2017, 9.4));
            gameRepository.save(new Game("Elden Ring", 2022, 9.6));
            gameRepository.save(new Game("Celeste", 2018, 9.1));
            gameRepository.save(new Game("DOOM Eternal", 2020, 8.9));
            gameRepository.save(new Game("Stardew Valley", 2016, 9.2));
            gameRepository.save(new Game("Hades", 2020, 9.5));
            gameRepository.save(new Game("The Witcher 3", 2015, 9.8));
            gameRepository.save(new Game("Cuphead", 2017, 8.8));
        }
    }
}
