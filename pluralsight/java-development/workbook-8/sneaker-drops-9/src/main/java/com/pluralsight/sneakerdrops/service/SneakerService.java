package com.pluralsight.sneakerdrops.service;

import com.pluralsight.sneakerdrops.data.BrandRepository;
import com.pluralsight.sneakerdrops.data.SneakerRepository;
import com.pluralsight.sneakerdrops.models.Brand;
import com.pluralsight.sneakerdrops.models.Sneaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SneakerService {

    private final SneakerRepository sneakerRepository;
    private final BrandRepository brandRepository;

    @Autowired
    public SneakerService(SneakerRepository sneakerRepository, BrandRepository brandRepository) {
        this.sneakerRepository = sneakerRepository;
        this.brandRepository = brandRepository;
    }

    public long count() { return sneakerRepository.count(); }
    public List<Sneaker> allSneakers() { return sneakerRepository.findAll(); }
    public List<Brand> allBrands() { return brandRepository.findAll(); }
    public List<Sneaker> byYear(int year) { return sneakerRepository.findByReleaseYear(year); }
    public List<Sneaker> byModel(String text) { return sneakerRepository.findByModelContaining(text); }
    public List<Sneaker> byMaxPrice(double price) { return sneakerRepository.findByPriceLessThan(price); }
    public List<Sneaker> search(double maxPrice, int minYear) { return sneakerRepository.search(maxPrice, minYear); }
    public List<Sneaker> byBrand(String brandName) { return sneakerRepository.findByBrand_Name(brandName); }

    public Sneaker byId(long id) {
        return sneakerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No sneaker with id " + id));
    }

    public Sneaker addSneaker(String model, double price, int year, long brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new NotFoundException("No brand with id " + brandId));
        return sneakerRepository.save(new Sneaker(model, price, year, brand));
    }

    public Sneaker updatePrice(long id, double price) {
        Sneaker sneaker = byId(id);
        sneaker.setPrice(price);
        return sneakerRepository.save(sneaker);
    }

    public void deleteSneaker(long id) {
        if (!sneakerRepository.existsById(id)) {
            throw new NotFoundException("No sneaker with id " + id);
        }
        sneakerRepository.deleteById(id);
    }

    public void seedIfEmpty() {
        if (sneakerRepository.count() > 0) {
            return;
        }
        Brand nike = brandRepository.save(new Brand("Nike"));
        Brand adidas = brandRepository.save(new Brand("Adidas"));
        Brand newBalance = brandRepository.save(new Brand("New Balance"));
        Brand puma = brandRepository.save(new Brand("Puma"));
        Brand reebok = brandRepository.save(new Brand("Reebok"));

        sneakerRepository.save(new Sneaker("Air Max 90", 129.99, 1990, nike));
        sneakerRepository.save(new Sneaker("Ultraboost", 179.99, 2015, adidas));
        sneakerRepository.save(new Sneaker("574", 89.99, 1988, newBalance));
        sneakerRepository.save(new Sneaker("Suede Classic", 74.99, 1968, puma));
        sneakerRepository.save(new Sneaker("Club C 85", 79.99, 1985, reebok));
        sneakerRepository.save(new Sneaker("Air Force 1", 109.99, 1982, nike));
        sneakerRepository.save(new Sneaker("Gazelle", 99.99, 1968, adidas));
        sneakerRepository.save(new Sneaker("Dunk Low", 119.99, 1985, nike));
    }
}
