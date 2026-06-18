package com.pluralsight.sneakerdrops.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Sneaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, message = "Model must be at least 2 characters")
    private String model;

    @DecimalMin("0.0")
    private double price;

    @Min(1900)
    private int releaseYear;

    @ManyToOne(optional = false)
    @NotNull
    private Brand brand;

    public Sneaker() { }

    public Sneaker(String model, double price, int releaseYear, Brand brand) {
        this.model = model;
        this.price = price;
        this.releaseYear = releaseYear;
        this.brand = brand;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }
    public Brand getBrand() { return brand; }
    public void setBrand(Brand brand) { this.brand = brand; }
}
