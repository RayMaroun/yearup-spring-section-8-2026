package com.pluralsight.sneakerdrops.service;

import com.pluralsight.sneakerdrops.data.BrandRepository;
import com.pluralsight.sneakerdrops.data.SneakerRepository;
import com.pluralsight.sneakerdrops.models.Brand;
import com.pluralsight.sneakerdrops.models.Sneaker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SneakerServiceTest {

    @Mock
    private SneakerRepository sneakerRepository;

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private SneakerService sneakerService;

    @Test
    void search_PriceRangeFilter_ReturnsOnlyInRange() {
        // Arrange
        Brand nike = new Brand("Nike");
        Sneaker cheap = new Sneaker("Cheap", 50.0, 2000, nike);
        Sneaker mid = new Sneaker("Mid", 150.0, 2010, nike);
        Sneaker pricey = new Sneaker("Pricey", 300.0, 2020, nike);
        when(sneakerRepository.findAll()).thenReturn(List.of(cheap, mid, pricey));

        // Act
        List<Sneaker> found = sneakerService.search(null, null, null, 100.0, 200.0, null);

        // Assert
        assertEquals(1, found.size());
        assertEquals("Mid", found.get(0).getModel());
    }
}
