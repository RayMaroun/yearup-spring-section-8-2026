package com.pluralsight.sneakerdrops.data;

import com.pluralsight.sneakerdrops.models.Brand;
import com.pluralsight.sneakerdrops.models.Sneaker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class SneakerRepositoryTest {

    @Autowired
    private SneakerRepository sneakerRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findById_AfterSave_ReturnsTheStoredSneaker() {
        // Arrange - persist a brand, then a sneaker that belongs to it
        Brand nike = entityManager.persistAndFlush(new Brand("Nike"));
        Sneaker saved = entityManager.persistAndFlush(new Sneaker("Air Max 90", 129.99, 1990, nike));

        // Act
        Sneaker found = sneakerRepository.findById(saved.getId()).orElseThrow();

        // Assert
        assertEquals("Air Max 90", found.getModel());
        assertEquals(1990, found.getReleaseYear());
        assertEquals(129.99, found.getPrice(), 0.001);
    }
}
