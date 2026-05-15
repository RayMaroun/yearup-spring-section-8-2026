package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Console storefront demo.
 * <p>
 * Features demonstrated
 * • Reading data from a pipe-delimited file (id|name|price)
 * • Storing inventory and a shopping cart in ArrayLists
 * • Simple user-input loop with basic validation
 * • Calculating totals and giving change at checkout
 * <p>
 * Deliberately kept simple: there is no “remove item” feature.
 */
public class Store {

    public static void main(String[] args) {

        /* -----------------------------------------------------------------
           1.  Prepare inventory and cart
           ----------------------------------------------------------------- */
        ArrayList<Product> inventory = new ArrayList<>();
        ArrayList<Product> cart = new ArrayList<>();

        loadInventory("products.csv", inventory);

        /* -----------------------------------------------------------------
           2.  Main menu loop
           ----------------------------------------------------------------- */
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 3) {
            System.out.println("\nWelcome to the Online Store!");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");

            /* Accept only 1, 2, or 3.  Any text input is rejected. */
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter 1, 2, or 3.");
                scanner.nextLine();        // discard the bad token
                continue;                  // restart the loop
            }
            choice = scanner.nextInt();
            scanner.nextLine();            // remove the newline that nextInt leaves

            switch (choice) {
                case 1 -> displayProducts(inventory, cart, scanner);
                case 2 -> displayCart(cart, scanner);
                case 3 -> System.out.println("Thank you for shopping with us!");
                default -> System.out.println("Invalid choice!");
            }
        }
        scanner.close();
    }

    /**
     * Reads a file whose lines look like   id|name|price
     * and fills the inventory list with Product objects.
     */
    public static void loadInventory(String fileName, ArrayList<Product> inventory) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");       // split on the pipe symbol
                Product product = new Product(
                        fields[0],                         // id
                        fields[1],                         // name
                        Double.parseDouble(fields[2])      // price
                );
                inventory.add(product);
            }
            reader.close();
            System.out.println("Loaded " + inventory.size() + " products.");
        } catch (IOException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }

    /**
     * Lists every product and lets the user add one to the cart.
     * If the user types X the method simply returns to the main menu.
     */
    public static void displayProducts(ArrayList<Product> inventory,
                                       ArrayList<Product> cart,
                                       Scanner scanner) {

        System.out.println("\nProducts");
        System.out.println("--------");
        for (Product p : inventory) {
            System.out.printf("%s  %s  $%.2f%n", p.getId(), p.getName(), p.getPrice());
        }

        System.out.print("Enter product ID to add to cart (or X to return): ");
        String id = scanner.nextLine().trim();

        if (id.equalsIgnoreCase("X")) return;      // user chose to go back

        Product product = findProductById(id, inventory);
        if (product == null) {
            System.out.println("That ID does not exist.");
        } else {
            cart.add(product);
            System.out.println("Added \"" + product.getName() + "\" to your cart.");
        }
    }

    /**
     * Shows everything in the cart, the running total,
     * and offers the option to check out.
     */
    public static void displayCart(ArrayList<Product> cart, Scanner scanner) {

        System.out.println("\nYour Cart");
        System.out.println("---------");

        if (cart.isEmpty()) {
            System.out.println("(empty)");
            return;                         // nothing more to do
        }

        double totalAmount = 0.0;
        for (Product p : cart) {
            System.out.printf("%s  $%.2f%n", p.getName(), p.getPrice());
            totalAmount += p.getPrice();
        }
        System.out.printf("Total: $%.2f%n", totalAmount);

        System.out.print("C = Check Out,  X = Return to menu: ");
        String choice = scanner.nextLine().trim();

        if (choice.equalsIgnoreCase("C")) {
            checkOut(cart, totalAmount, scanner);
        }   // any other input returns to menu
    }

    /**
     * Finalises the purchase.
     * Steps:
     * 1. Ask if the user really wants to pay.
     * 2. Take payment amount.
     * 3. Calculate change.
     * 4. Print a receipt and clear the cart.
     */
    public static void checkOut(ArrayList<Product> cart,
                                double totalAmount,
                                Scanner scanner) {

        System.out.printf("Total amount owed: $%.2f%n", totalAmount);
        System.out.print("Proceed with purchase? (Y/N): ");
        if (!scanner.nextLine().trim().equalsIgnoreCase("Y")) {
            System.out.println("Checkout cancelled.");
            return;
        }

        System.out.print("Enter payment amount: $");
        double payment = scanner.nextDouble();
        scanner.nextLine();                        // clear newline

        if (payment < totalAmount) {
            System.out.println("Insufficient payment. Transaction cancelled.");
            return;
        }

        double change = payment - totalAmount;
        System.out.printf("Change: $%.2f%n", change);

        System.out.println("\nItems purchased:");
        for (Product p : cart) {
            System.out.printf("• %s  $%.2f%n", p.getName(), p.getPrice());
        }
        cart.clear();
        System.out.println("Thank you for your purchase!");
    }

    /**
     * Searches a list for the given ID.
     * Returns the Product if found, or null if not found.
     */
    public static Product findProductById(String id, ArrayList<Product> inventory) {
        for (Product p : inventory) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }
}
