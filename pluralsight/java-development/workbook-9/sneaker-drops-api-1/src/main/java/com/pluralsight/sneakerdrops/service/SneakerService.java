package com.pluralsight.sneakerdrops.service;

import com.pluralsight.sneakerdrops.data.BrandRepository;
import com.pluralsight.sneakerdrops.data.SneakerRepository;
import com.pluralsight.sneakerdrops.models.Brand;
import com.pluralsight.sneakerdrops.models.Sneaker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SneakerService {

    private final SneakerRepository sneakerRepository;
    private final BrandRepository brandRepository;

    public SneakerService(SneakerRepository sneakerRepository, BrandRepository brandRepository) {
        this.sneakerRepository = sneakerRepository;
        this.brandRepository = brandRepository;
    }

    public List<Sneaker> allSneakers() { return sneakerRepository.findAll(); }
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
}
