package com.pluralsight;

public abstract class Asset {
    private String description;
    private int yearAcquired;
    private double originalCost;

    public Asset(String description, int yearAcquired, double originalCost) {
        this.description = description;
        this.yearAcquired = yearAcquired;
        this.originalCost = originalCost;
    }

    public String getDescription() {
        return description;
    }

    public int getYearAcquired() {
        return yearAcquired;
    }

    public abstract double getValue();

    public void printMessage(){
        System.out.println("Potato");
    }
}
