package com.pluralsight.sneakerdrops;

import com.pluralsight.sneakerdrops.data.BrandRepository;
import com.pluralsight.sneakerdrops.models.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final BrandRepository brandRepository;

    @Autowired
    public StartupRunner(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void run(String... args) {
        seedData();
        for (Brand b : brandRepository.findAll()) {
            System.out.println(b.getId() + " - " + b.getName());
        }
    }

    // Puts a little starting data in the database, but only when it is empty,
    // so re-running the app never creates duplicates. We grow this method as the
    // application grows.
    private void seedData() {
        if (brandRepository.count() == 0) {
            brandRepository.save(new Brand("Nike"));
            brandRepository.save(new Brand("Adidas"));
            brandRepository.save(new Brand("New Balance"));
            brandRepository.save(new Brand("Puma"));
            brandRepository.save(new Brand("Reebok"));
        }
    }
}
