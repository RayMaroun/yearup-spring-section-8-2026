package com.pluralsight;

import java.util.Scanner;

/**
 * A small, console–based library application.
 * <p>
 * Students can:
 * <ul>
 *   <li>view books that are available to borrow</li>
 *   <li>view books that are already checked out</li>
 *   <li>check out a book or return it</li>
 * </ul>
 * The program keeps running until the user chooses “Exit”.
 */
public class NeighborhoodLibrary {

    // Menu options described by constants so the numbers have clear meaning
    private static final int MENU_SHOW_AVAILABLE = 1;
    private static final int MENU_SHOW_CHECKEDOUT = 2;
    private static final int MENU_EXIT = 3;

    public static void main(String[] args) {

        /* -------------------------- create a small inventory --------------------------- */
        Book[] inventory = {
                new Book(1, "9780671023379", "The Alchemist by Paulo Coelho"),
                new Book(2, "9780061120084", "To Kill a Mockingbird by Harper Lee"),
                new Book(3, "9780143132172", "The 7 Habits of Highly Effective People by Stephen Covey"),
                new Book(4, "9781984801906", "Atomic Habits by James Clear"),
                new Book(5, "9780062562851", "The Four Agreements by Don Miguel Ruiz")
        };

        Scanner scanner = new Scanner(System.in);

        int choice = -1;                                 // keeps the loop running
        while (choice != MENU_EXIT) {
            /* ----------------------------- main menu ----------------------------- */
            System.out.println("\nStore Home Screen");
            System.out.println("------------------");
            System.out.println("1. Show Available Books");
            System.out.println("2. Show Checked Out Books");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");

            /* ---------- make sure the user types a number, not letters ---------- */
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                scanner.nextLine();                      // discard the bad input
                continue;                               // restart menu loop
            }

            choice = scanner.nextInt();
            scanner.nextLine();                         // remove the leftover newline

            switch (choice) {
                case MENU_SHOW_AVAILABLE -> displayAvailableBooks(inventory, scanner);
                case MENU_SHOW_CHECKEDOUT -> displayCheckedOutBooks(inventory, scanner);
                case MENU_EXIT -> System.out.println("Exiting the application. Goodbye!");
                default -> System.out.println("Invalid choice!");
            }
        }
        scanner.close();                                // always close Scanner when finished
    }

    /**
     * Finds a book by its id.
     *
     * @param inventory the array of books
     * @param id        the id to look for
     * @return the matching {@code Book} or {@code null} if not found
     */
    private static Book findBookById(Book[] inventory, int id) {
        for (Book b : inventory) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    /**
     * Lists all books that are not checked out and lets the user borrow one.
     *
     * @param inventory the array of books
     * @param scanner   shared scanner for user input
     */
    private static void displayAvailableBooks(Book[] inventory, Scanner scanner) {
        System.out.println("\nAvailable Books");
        System.out.println("------------------");

        // show each available book, one per line
        for (Book book : inventory) {
            if (!book.isCheckedOut()) {
                System.out.printf("Id: %d  ISBN: %s  Title: %s%n",
                        book.getId(), book.getIsbn(), book.getTitle());
            }
        }
        System.out.println("------------------");
        System.out.print("Enter the Id of the book to check out (0 to cancel): ");

        if (!scanner.hasNextInt()) {                    // validate numeric id
            System.out.println("Please enter a number.");
            scanner.nextLine();
            return;                                     // return to main menu
        }
        int bookId = scanner.nextInt();
        scanner.nextLine();

        if (bookId == 0) {                              // user changed their mind
            return;
        }

        Book selectedBook = findBookById(inventory, bookId);
        if (selectedBook == null) {                     // id does not exist
            System.out.println("Invalid book Id!");
            return;
        }
        if (selectedBook.isCheckedOut()) {              // double-check status
            System.out.println("Sorry, that book is already checked out!");
            return;
        }

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        selectedBook.checkOut(name);                    // mark book as borrowed
        System.out.println("Thank you, " + name +
                "! You checked out \"" + selectedBook.getTitle() + "\".");
    }

    /**
     * Lists all books that are currently checked out and lets the user return one.
     *
     * @param inventory the array of books
     * @param scanner   shared scanner for user input
     */
    private static void displayCheckedOutBooks(Book[] inventory, Scanner scanner) {
        System.out.println("\nChecked Out Books");
        System.out.println("------------------");

        for (Book book : inventory) {
            if (book.isCheckedOut()) {
                System.out.printf("Id: %d  ISBN: %s  Title: %s  →  %s%n",
                        book.getId(), book.getIsbn(), book.getTitle(),
                        book.getCheckedOutTo());
            }
        }
        System.out.println("------------------");
        System.out.print("Press C to check in a book, or X to return: ");
        String input = scanner.nextLine().trim();

        // If user wants to check a book in
        if (input.equalsIgnoreCase("C")) {
            System.out.print("Enter the Id of the book to check in: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                scanner.nextLine();
                return;
            }
            int id = scanner.nextInt();
            scanner.nextLine();

            Book book = findBookById(inventory, id);
            if (book == null) {
                System.out.println("Invalid book Id!");
            } else if (!book.isCheckedOut()) {
                System.out.println("That book is not checked out!");
            } else {
                book.checkIn();                         // mark book as returned
                System.out.println("Thank you for returning \"" + book.getTitle() + "\".");
            }
        } else if (!input.equalsIgnoreCase("X")) {      // any input except X is invalid
            System.out.println("Invalid input!");
        }
    }
}
