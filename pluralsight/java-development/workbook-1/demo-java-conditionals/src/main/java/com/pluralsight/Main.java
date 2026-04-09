package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your name:");
        String name = scanner.nextLine();

        System.out.println("Enter your age:");
        int age = scanner.nextInt();

/*        if (age > 25) {
            System.out.println("You are allowed to drink!");
        } else {
            System.out.println("You are not allowed to drink!");
        }*/

/*        double price;

        if (age < 18) {
            price = 22.50;
        } else {
            price = 25.00;
        }

        System.out.println("The price is: " + price);*/

/*        double price;

        if (age < 18) {
            price = 22.50;
        } else if (age < 65) {
            price = 30.50;
        } else {
            price = 25.00;
        }

        System.out.println("The price is: " + price);*/

/*        double price;
        if (age < 18 || age >= 65) {
            price = 18.00;
        } else {
            price = 25.00;
        }

        System.out.println("The price is: " + price);*/


/*        double price;

        if (age < 18) {
            price = 22.50;
        } else {
            price = 25.00;
        }*/

/*        double price = (age < 18) ? 22.50 : 25.00;

        System.out.println("The price is: " + price);*/

        if (name.equalsIgnoreCase("Raymond")) {
            System.out.println("I love Potatoes!");
        } else {
            System.out.println("I don't love Potatoes!");
        }

    }
}
