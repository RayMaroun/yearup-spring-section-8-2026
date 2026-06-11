package com.pluralsight.gamelibrary;

import com.pluralsight.gamelibrary.models.Game;
import com.pluralsight.gamelibrary.models.Genre;
import com.pluralsight.gamelibrary.service.GameService;
import com.pluralsight.gamelibrary.service.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class StartupRunner implements CommandLineRunner {

    private final GameService gameService;

    @Autowired
    public StartupRunner(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) {
        gameService.seedIfEmpty();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu();
            try {
                switch (scanner.nextInt()) {
                    case 1 -> listGames();
                    case 2 -> findByYear(scanner);
                    case 3 -> findByTitle(scanner);
                    case 4 -> findByRating(scanner);
                    case 5 -> advancedSearch(scanner);
                    case 6 -> viewById(scanner);
                    case 7 -> addGame(scanner);
                    case 8 -> updateRating(scanner);
                    case 9 -> deleteGame(scanner);
                    case 10 -> listByGenre(scanner);
                    case 0 -> running = false;
                    default -> System.out.println("Unknown option.");
                }
            } catch (NotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Game Library ===");
        System.out.println("1) List all games");
        System.out.println("2) Find by release year");
        System.out.println("3) Search by title");
        System.out.println("4) Find by minimum rating");
        System.out.println("5) Advanced search (rating + year)");
        System.out.println("6) View one game by id");
        System.out.println("7) Add a game");
        System.out.println("8) Update a game's rating");
        System.out.println("9) Delete a game");
        System.out.println("10) List games by genre");
        System.out.println("0) Quit");
        System.out.print("Choose: ");
    }

    private void listGames() {
        System.out.println("You have " + gameService.count() + " games:");
        for (Game g : gameService.allGames()) {
            System.out.println(g.getId() + " - " + g.getTitle() + " [" + g.getGenre().getName() + "]");
        }
    }

    private void findByYear(Scanner scanner) {
        System.out.print("Year: ");
        int year = scanner.nextInt();
        for (Game g : gameService.byYear(year)) {
            System.out.println(g.getTitle() + " (" + g.getReleaseYear() + ")");
        }
    }

    private void findByTitle(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Title contains: ");
        String text = scanner.nextLine();
        for (Game g : gameService.byTitle(text)) {
            System.out.println(g.getTitle());
        }
    }

    private void findByRating(Scanner scanner) {
        System.out.print("Minimum rating: ");
        double min = scanner.nextDouble();
        for (Game g : gameService.byMinRating(min)) {
            System.out.println(g.getTitle() + " (" + g.getRating() + ")");
        }
    }

    private void advancedSearch(Scanner scanner) {
        System.out.print("Minimum rating: ");
        double minRating = scanner.nextDouble();
        System.out.print("Released on or after year: ");
        int minYear = scanner.nextInt();
        for (Game g : gameService.search(minRating, minYear)) {
            System.out.println(g.getTitle() + " (" + g.getRating() + ", " + g.getReleaseYear() + ")");
        }
    }

    private void viewById(Scanner scanner) {
        System.out.print("Game id: ");
        long id = scanner.nextLong();
        Game game = gameService.byId(id);
        System.out.println(game.getId() + " - " + game.getTitle() + " (" + game.getRating() + ")");
    }

    private void addGame(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Release year: ");
        int year = scanner.nextInt();
        System.out.print("Rating: ");
        double rating = scanner.nextDouble();
        System.out.println("Choose a genre:");
        for (Genre genre : gameService.allGenres()) {
            System.out.println(genre.getId() + " - " + genre.getName());
        }
        System.out.print("Genre id: ");
        long genreId = scanner.nextLong();
        gameService.addGame(title, year, rating, genreId);
        System.out.println("Added!");
    }

    private void updateRating(Scanner scanner) {
        System.out.print("Game id: ");
        long id = scanner.nextLong();
        System.out.print("New rating: ");
        double rating = scanner.nextDouble();
        gameService.updateRating(id, rating);
        System.out.println("Updated!");
    }

    private void deleteGame(Scanner scanner) {
        System.out.print("Game id: ");
        long id = scanner.nextLong();
        gameService.deleteGame(id);
        System.out.println("Deleted.");
    }

    private void listByGenre(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Genre name: ");
        String name = scanner.nextLine();
        for (Game g : gameService.byGenre(name)) {
            System.out.println(g.getTitle());
        }
    }
}
