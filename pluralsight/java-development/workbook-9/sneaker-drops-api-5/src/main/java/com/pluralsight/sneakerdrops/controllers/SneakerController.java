package com.pluralsight.sneakerdrops.controllers;

import com.pluralsight.sneakerdrops.models.Sneaker;
import com.pluralsight.sneakerdrops.service.SneakerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/sneakers")
@CrossOrigin
public class SneakerController {

    private final SneakerService sneakerService;

    public SneakerController(SneakerService sneakerService) {
        this.sneakerService = sneakerService;
    }

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
        Sneaker sneaker = sneakerService.byId(id);
        if (sneaker == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No sneaker with id " + id);
        }
        return sneaker;
    }

    @PostMapping
    public ResponseEntity<Sneaker> create(@Valid @RequestBody Sneaker sneaker) {
        Sneaker saved = sneakerService.createSneaker(sneaker);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public Sneaker update(@PathVariable long id, @Valid @RequestBody Sneaker sneaker) {
        Sneaker saved = sneakerService.updateSneaker(id, sneaker);
        if (saved == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No sneaker with id " + id);
        }
        return saved;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        if (sneakerService.byId(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No sneaker with id " + id);
        }
        sneakerService.deleteSneaker(id);
        return ResponseEntity.noContent().build();
    }
}
