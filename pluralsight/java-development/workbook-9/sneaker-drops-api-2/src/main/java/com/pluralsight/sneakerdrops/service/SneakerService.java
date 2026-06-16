package com.pluralsight.sneakerdrops.service;

import com.pluralsight.sneakerdrops.data.BrandRepository;
import com.pluralsight.sneakerdrops.data.SneakerRepository;
import com.pluralsight.sneakerdrops.models.Brand;
import com.pluralsight.sneakerdrops.models.Sneaker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SneakerService {

    private final SneakerRepository sneakerRepository;
    private final BrandRepository brandRepository;

    public SneakerService(SneakerRepository sneakerRepository, BrandRepository brandRepository) {
        this.sneakerRepository = sneakerRepository;
        this.brandRepository = brandRepository;
    }

    // Adapted for the web: returns null when missing (the controller turns it into a 404).
    public Sneaker byId(long id) {
        return sneakerRepository.findById(id).orElse(null);
    }

    // One combinable stream search, replacing last week's per-field finders.
    public List<Sneaker> search(Integer year, String model, String brand,
                                Double minPrice, Double maxPrice, String sort) {
        List<Sneaker> results = new ArrayList<>(sneakerRepository.findAll().stream()
                .filter(s -> year == null || s.getReleaseYear() == year)
                .filter(s -> model == null || s.getModel().toLowerCase().contains(model.toLowerCase()))
                .filter(s -> brand == null
                        || (s.getBrand() != null && s.getBrand().getName().equalsIgnoreCase(brand)))
                .filter(s -> minPrice == null || s.getPrice() >= minPrice)
                .filter(s -> maxPrice == null || s.getPrice() <= maxPrice)
                .toList());

        if ("price".equalsIgnoreCase(sort)) {
            results.sort(Comparator.comparingDouble(Sneaker::getPrice));
        } else if ("model".equalsIgnoreCase(sort)) {
            results.sort(Comparator.comparing(Sneaker::getModel));
        }
        return results;
    }

    // Still last week's write methods - rebuilt for the web in Module 4.
    public Sneaker addSneaker(String model, double price, int year, long brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new NotFoundException("No brand with id " + brandId));
        return sneakerRepository.save(new Sneaker(model, price, year, brand));
    }

    public Sneaker updatePrice(long id, double price) {
        Sneaker sneaker = sneakerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No sneaker with id " + id));
        sneaker.setPrice(price);
        return sneakerRepository.save(sneaker);
    }

    public void deleteSneaker(long id) {
        if (!sneakerRepository.existsById(id)) {
            throw new NotFoundException("No sneaker with id " + id);
        }
        sneakerRepository.deleteById(id);
    }
}
