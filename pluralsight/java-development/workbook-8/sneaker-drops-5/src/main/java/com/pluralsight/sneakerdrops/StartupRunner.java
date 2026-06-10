package com.pluralsight.sneakerdrops;

import com.pluralsight.sneakerdrops.data.BrandRepository;
import com.pluralsight.sneakerdrops.data.SneakerRepository;
import com.pluralsight.sneakerdrops.models.Brand;
import com.pluralsight.sneakerdrops.models.Sneaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class StartupRunner implements CommandLineRunner {

    private final SneakerRepository sneakerRepository;
    private final BrandRepository brandRepository;   // also injected, so we can seed data

    @Autowired
    public StartupRunner(SneakerRepository sneakerRepository, BrandRepository brandRepository) {
        this.sneakerRepository = sneakerRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public void run(String... args) {
        seedData();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n=== Sneaker Drops ===");
            System.out.println("1) List all sneakers");
            System.out.println("2) Find by release year");
            System.out.println("3) Search by model");
            System.out.println("4) Find by maximum price");
            System.out.println("0) Quit");
            System.out.print("Choose: ");

            switch (scanner.nextInt()) {
                case 1 -> listSneakers();
                case 2 -> findByYear(scanner);
                case 3 -> findByModel(scanner);
                case 4 -> findByPrice(scanner);
                case 0 -> running = false;
                default -> System.out.println("Unknown option.");
            }
        }
    }

    private void listSneakers() {
        System.out.println("You have " + sneakerRepository.count() + " sneakers:");
        for (Sneaker s : sneakerRepository.findAll()) {
            System.out.println(s.getId() + " - " + s.getModel() + " (" + s.getPrice() + ")");
        }
    }

    private void findByYear(Scanner scanner) {
        System.out.print("Year: ");
        int year = scanner.nextInt();
        for (Sneaker s : sneakerRepository.findByReleaseYear(year)) {
            System.out.println(s.getModel() + " (" + s.getReleaseYear() + ")");
        }
    }

    private void findByModel(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Model contains: ");
        String text = scanner.nextLine();
        for (Sneaker s : sneakerRepository.findByModelContaining(text)) {
            System.out.println(s.getModel());
        }
    }

    private void findByPrice(Scanner scanner) {
        System.out.print("Maximum price: ");
        double max = scanner.nextDouble();
        for (Sneaker s : sneakerRepository.findByPriceLessThan(max)) {
            System.out.println(s.getModel() + " (" + s.getPrice() + ")");
        }
    }

    // Seeds starting data when the database is empty (idempotent). Brands and
    // sneakers are seeded independently; sneakers have no brand until Module 4.
    private void seedData() {
        if (brandRepository.count() == 0) {
            brandRepository.save(new Brand("Nike"));
            brandRepository.save(new Brand("Adidas"));
            brandRepository.save(new Brand("New Balance"));
            brandRepository.save(new Brand("Puma"));
            brandRepository.save(new Brand("Reebok"));
        }
        if (sneakerRepository.count() == 0) {
            sneakerRepository.save(new Sneaker("Air Max 90", 129.99, 1990));
            sneakerRepository.save(new Sneaker("Ultraboost", 179.99, 2015));
            sneakerRepository.save(new Sneaker("574", 89.99, 1988));
            sneakerRepository.save(new Sneaker("Suede Classic", 74.99, 1968));
            sneakerRepository.save(new Sneaker("Club C 85", 79.99, 1985));
            sneakerRepository.save(new Sneaker("Air Force 1", 109.99, 1982));
            sneakerRepository.save(new Sneaker("Gazelle", 99.99, 1968));
            sneakerRepository.save(new Sneaker("Dunk Low", 119.99, 1985));
        }
    }
}
