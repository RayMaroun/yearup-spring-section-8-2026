package com.pluralsight;

public class Car {
    // Attributes
    private String make;
    private String model;
    private String color;
    private int year;
    private long odometerReading;
    private double price;
    private int currentSpeed;

    // Constructor
    public Car(String make, String model, String color, int year, double price) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.odometerReading = 0;
        this.price = price;
        this.currentSpeed = 0;
    }

    // Getters and Setters
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getOdometerReading() {
        return odometerReading;
    }

    public void setOdometerReading(long odometerReading) {
        this.odometerReading = odometerReading;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    // Actions
    public void travel(long distance) {
        this.odometerReading += distance;
    }

    public void accelerate(int speedIncrement) {
        this.currentSpeed += speedIncrement;
    }

    public void brake(int speedDecrement) {
        /*this.currentSpeed -= speedDecrement;*/
/*        if (speedDecrement > this.currentSpeed) {
            this.currentSpeed = 0;
        } else {
            this.currentSpeed = this.currentSpeed - speedDecrement;
        }*/

        this.currentSpeed = (speedDecrement > this.currentSpeed) ? 0 : this.currentSpeed - speedDecrement;

    }
}
