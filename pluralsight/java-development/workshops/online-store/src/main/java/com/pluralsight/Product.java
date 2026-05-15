package com.pluralsight;

/**
 * Simple data holder for a store item.
 * <p>
 * Fields
 * • id    - string code the user types (for example “A17”)
 * • name  - product title
 * • price - selling price
 */
public class Product {

    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /* Getters (read-only access) */

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
