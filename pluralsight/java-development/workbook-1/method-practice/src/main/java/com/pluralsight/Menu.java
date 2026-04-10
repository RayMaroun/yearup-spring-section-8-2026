package com.pluralsight;

public class Menu {
    public static void main(String[] args) {
        displayMenu();   // first call
        displayMenu();   // second call (reusability)
    }

    public static void displayMenu() {
        System.out.println("=== MENU ===");
        System.out.println("1. Coffee - $3.99");
        System.out.println("2. Tea - $2.99");
        System.out.println("3. Cookie - $1.99");
    }
}
