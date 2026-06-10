package com.pluralsight.sneakerdrops.data;

import com.pluralsight.sneakerdrops.models.Sneaker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SneakerRepository extends JpaRepository<Sneaker, Long> {

    List<Sneaker> findByReleaseYear(int year);

    List<Sneaker> findByModelContaining(String text);

    List<Sneaker> findByPriceLessThan(double price);
}
