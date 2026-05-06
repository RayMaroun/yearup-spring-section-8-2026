package com.pluralsight;

public class Car {
    private String make;
    private String model;
    private int speed;

    public Car() {
    }

    public Car(String make, String model) {
        this.make = make;
        this.model = model;
        this.speed = 0;
    }

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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void accelerate(int changeInSpeed) {
        this.speed += changeInSpeed;
    }

    public void brake(int changeInSpeed) {
        if (changeInSpeed > this.speed) {
            this.speed = 0;
        } else {
            this.speed -= changeInSpeed;
        }
    }
}
