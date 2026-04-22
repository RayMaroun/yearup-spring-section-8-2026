package com.pluralsight;

import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("skills.txt");

            writer.write("Hello Everyone\n");
            writer.write("I am your Potato instructor!\n");
            writer.write("Welcome to the Java Bootcamp!");

            writer.close();

        } catch (Exception ex) {
            System.err.println("An error has occurred!");
        }
    }
}
