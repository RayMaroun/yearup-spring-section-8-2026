package com.pluralsight.sneakerdrops.models;

import jakarta.persistence.*;

@Entity
public class Sneaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private double price;
    private int releaseYear;

    @ManyToOne(optional = false)
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
