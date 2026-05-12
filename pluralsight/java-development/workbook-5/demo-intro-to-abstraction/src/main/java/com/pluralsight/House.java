package com.pluralsight;

public class House extends Asset {
    private String address;
    private int squareFeet;
    private int lotSize;

    public House(String description, int yearAcquired, double originalCost, String address, int squareFeet, int lotSize) {
        super(description, yearAcquired, originalCost);
        this.address = address;
        this.squareFeet = squareFeet;
        this.lotSize = lotSize;
    }

    public String getAddress() {
        return address;
    }

    public int getSquareFeet() {
        return squareFeet;
    }

    public int getLotSize() {
        return lotSize;
    }

    @Override
    public double getValue() {
        return (180 * squareFeet) + (0.25 * lotSize);
    }
}
