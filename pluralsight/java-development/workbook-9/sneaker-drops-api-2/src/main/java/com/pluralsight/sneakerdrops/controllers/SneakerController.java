package com.pluralsight.sneakerdrops.controllers;

import com.pluralsight.sneakerdrops.models.Sneaker;
import com.pluralsight.sneakerdrops.service.SneakerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sneakers")
@CrossOrigin
public class SneakerController {

    private final SneakerService sneakerService;

    public SneakerController(SneakerService sneakerService) {
        this.sneakerService = sneakerService;
    }

    // Filters combine; minPrice + maxPrice give a price range.
    @GetMapping
    public List<Sneaker> getAll(@RequestParam(required = false) Integer year,
                                @RequestParam(required = false) String model,
                                @RequestParam(required = false) String brand,
                                @RequestParam(required = false) Double minPrice,
                                @RequestParam(required = false) Double maxPrice,
                                @RequestParam(required = false) String sort) {
        return sneakerService.search(year, model, brand, minPrice, maxPrice, sort);
    }

    @GetMapping("/{id}")
    public Sneaker getById(@PathVariable long id) {
        return sneakerService.byId(id);
    }
}
