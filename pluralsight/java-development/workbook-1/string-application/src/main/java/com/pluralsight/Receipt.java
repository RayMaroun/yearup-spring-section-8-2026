package com.pluralsight;

public class Receipt {
    public static void main(String[] args) {
        // Step 3: Create variables for item
        String itemName = "Apples";
        double itemPrice = 2.37;

        // Step 3: Create quantity
        int quantity = 3;

        // Step 3: Calculate total
        double total = quantity * itemPrice;

        // Step 3: Build and print
        System.out.println("You bought " + quantity + " " + itemName + " for $" + total);
    }
}
