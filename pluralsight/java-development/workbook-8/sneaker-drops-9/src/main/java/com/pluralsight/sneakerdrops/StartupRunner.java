package com.pluralsight.sneakerdrops;

import com.pluralsight.sneakerdrops.models.Brand;
import com.pluralsight.sneakerdrops.models.Sneaker;
import com.pluralsight.sneakerdrops.service.NotFoundException;
import com.pluralsight.sneakerdrops.service.SneakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class StartupRunner implements CommandLineRunner {

    private final SneakerService sneakerService;

    @Autowired
    public StartupRunner(SneakerService sneakerService) {
        this.sneakerService = sneakerService;
    }

    @Override
    public void run(String... args) {
        sneakerService.seedIfEmpty();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu();
            try {
                switch (scanner.nextInt()) {
                    case 1 -> listSneakers();
                    case 2 -> findByYear(scanner);
                    case 3 -> findByModel(scanner);
                    case 4 -> findByPrice(scanner);
                    case 5 -> advancedSearch(scanner);
                    case 6 -> viewById(scanner);
                    case 7 -> addSneaker(scanner);
                    case 8 -> updatePrice(scanner);
                    case 9 -> deleteSneaker(scanner);
                    case 10 -> listByBrand(scanner);
                    case 0 -> running = false;
                    default -> System.out.println("Unknown option.");
                }
            } catch (NotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Sneaker Drops ===");
        System.out.println("1) List all sneakers");
        System.out.println("2) Find by release year");
        System.out.println("3) Search by model");
        System.out.println("4) Find by maximum price");
        System.out.println("5) Advanced search (price + year)");
        System.out.println("6) View one sneaker by id");
        System.out.println("7) Add a sneaker");
        System.out.println("8) Update a sneaker's price");
        System.out.println("9) Delete a sneaker");
        System.out.println("10) List sneakers by brand");
        System.out.println("0) Quit");
        System.out.print("Choose: ");
    }

    private void listSneakers() {
        System.out.println("You have " + sneakerService.count() + " sneakers:");
        for (Sneaker s : sneakerService.allSneakers()) {
            System.out.println(s.getId() + " - " + s.getModel() + " [" + s.getBrand().getName() + "]");
        }
    }

    private void findByYear(Scanner scanner) {
        System.out.print("Year: ");
        int year = scanner.nextInt();
        for (Sneaker s : sneakerService.byYear(year)) {
            System.out.println(s.getModel() + " (" + s.getReleaseYear() + ")");
        }
    }

    private void findByModel(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Model contains: ");
        String text = scanner.nextLine();
        for (Sneaker s : sneakerService.byModel(text)) {
            System.out.println(s.getModel());
        }
    }

    private void findByPrice(Scanner scanner) {
        System.out.print("Maximum price: ");
        double max = scanner.nextDouble();
        for (Sneaker s : sneakerService.byMaxPrice(max)) {
            System.out.println(s.getModel() + " (" + s.getPrice() + ")");
        }
    }

    private void advancedSearch(Scanner scanner) {
        System.out.print("Maximum price: ");
        double maxPrice = scanner.nextDouble();
        System.out.print("Released on or after year: ");
        int minYear = scanner.nextInt();
        for (Sneaker s : sneakerService.search(maxPrice, minYear)) {
            System.out.println(s.getModel() + " (" + s.getPrice() + ", " + s.getReleaseYear() + ")");
        }
    }

    private void viewById(Scanner scanner) {
        System.out.print("Sneaker id: ");
        long id = scanner.nextLong();
        Sneaker sneaker = sneakerService.byId(id);
        System.out.println(sneaker.getId() + " - " + sneaker.getModel() + " (" + sneaker.getPrice() + ")");
    }

    private void addSneaker(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.print("Release year: ");
        int year = scanner.nextInt();
        System.out.println("Choose a brand:");
        for (Brand brand : sneakerService.allBrands()) {
            System.out.println(brand.getId() + " - " + brand.getName());
        }
        System.out.print("Brand id: ");
        long brandId = scanner.nextLong();
        sneakerService.addSneaker(model, price, year, brandId);
        System.out.println("Added!");
    }

    private void updatePrice(Scanner scanner) {
        System.out.print("Sneaker id: ");
        long id = scanner.nextLong();
        System.out.print("New price: ");
        double price = scanner.nextDouble();
        sneakerService.updatePrice(id, price);
        System.out.println("Updated!");
    }

    private void deleteSneaker(Scanner scanner) {
        System.out.print("Sneaker id: ");
        long id = scanner.nextLong();
        sneakerService.deleteSneaker(id);
        System.out.println("Deleted.");
    }

    private void listByBrand(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Brand name: ");
        String name = scanner.nextLine();
        for (Sneaker s : sneakerService.byBrand(name)) {
            System.out.println(s.getModel());
        }
    }
}
