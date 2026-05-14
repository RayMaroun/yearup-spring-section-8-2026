package com.pluralsight.interfaces;

import java.awt.*;

public interface IMovable {
    Point move(int xUnits, int yUnits);

    void goHome();

    default void print() {
        System.out.println("I can move!");
    }
}
