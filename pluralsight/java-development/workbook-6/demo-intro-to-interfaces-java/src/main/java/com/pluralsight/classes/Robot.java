package com.pluralsight.classes;

import com.pluralsight.interfaces.IMovable;

import java.awt.*;

public class Robot implements IMovable {
    private String name;
    private Point currentLocation;
    private int power;

    public Robot(String name) {
        this.name = name;
        this.currentLocation = new Point(0, 0);
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
        int biggestUnit = Math.max(xUnits, yUnits);

        if (power >= biggestUnit) {
            currentLocation = new Point(currentLocation.x + xUnits, currentLocation.y + yUnits);
            power -= biggestUnit;
        }
        return currentLocation;
    }

    @Override
    public void goHome() {
        this.currentLocation = new Point(0, 0);
    }
}
