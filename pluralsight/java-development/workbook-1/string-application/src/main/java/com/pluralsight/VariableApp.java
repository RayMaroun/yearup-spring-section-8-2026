package com.pluralsight;

public class VariableApp {
    public static void main(String[] args) {
        // Step 1: Declare variables
        String favoriteColor = "Blue";
        int yearStarted = 2025;
        char middleInitial = 'M';
        boolean hasPets = true;
        String niceMessage = "Keep pushing forward, success is near!";

        // Step 1: Print each variable with a descriptive message
        System.out.println("My favorite color is: " + favoriteColor);
        System.out.println("The year I started this class is: " + yearStarted);
        System.out.println("My middle initial is: " + middleInitial);
        System.out.println("Do I have pets? " + hasPets);
        System.out.println("Here is a nice message: " + niceMessage);

        // Step 2: Declare AND initialize variables
        int daysInWeek = 7;
        double coffeePrice = 4.99;
        char favoriteLetter = 'R';
        boolean isRaining = false;

        // Step 2: Print all values in complete sentences
        System.out.println("There are " + daysInWeek + " days in a week.");
        System.out.println("The price of coffee is $" + coffeePrice);
        System.out.println("My favorite letter is: " + favoriteLetter);
        System.out.println("Is it raining? " + isRaining);
    }
}
