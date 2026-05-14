package com.pluralsight.classes;

import com.pluralsight.interfaces.IMovable;

import java.awt.*;

public class Turtle implements IMovable {
    private String name;
    private Point currentLocation;
    private int power;

    public Turtle(String name) {
        this.name = name;
        this.currentLocation = new Point(25, 25);
        this.power = 100;
    }

    public String getName() {
        return name;
    }

    public Point getCurrentLocation() {
        return currentLocation;
    }

    public int getPower() {
        return power;
    }

    @Override
    public Point move(int xUnits, int yUnits) {
        currentLocation = new Point(currentLocation.x + xUnits, currentLocation.y + yUnits);
        return currentLocation;
    }

    @Override
    public void goHome() {
        this.currentLocation = new Point(25, 25);
    }
}
