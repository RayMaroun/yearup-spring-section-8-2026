package com.pluralsight;

/**
 * Represents a single book in the library.
 * A book can be checked out to exactly one person at a time.
 */
public class Book {

    /* --------------------------------------------------------------------------
       Fields
       -------------------------------------------------------------------------- */

    private int id;           // numeric id (chosen by the library)
    private String isbn;         // international standard book number
    private String title;        // book title
    private boolean checkedOut;   // true if currently borrowed
    private String checkedOutTo; // name of the borrower (empty when in library)

    /* --------------------------------------------------------------------------
       Constructor
       -------------------------------------------------------------------------- */

    /**
     * Creates a new book that starts on the shelf (not checked out).
     *
     * @param id    numeric id assigned by the library
     * @param isbn  ISBN of the book
     * @param title title and author
     */
    public Book(int id, String isbn, String title) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.checkedOut = false;
        this.checkedOutTo = "";
    }

    /* --------------------------------------------------------------------------
       Getters
       -------------------------------------------------------------------------- */

    public int getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public String getCheckedOutTo() {
        return checkedOutTo;
    }

    /* --------------------------------------------------------------------------
       Public actions
       -------------------------------------------------------------------------- */

    /**
     * Checks the book out to the given person.
     *
     * @param name borrower’s name
     */
    public void checkOut(String name) {
        this.checkedOut = true;
        this.checkedOutTo = name;
    }

    /**
     * Returns the book to the library.
     */
    public void checkIn() {
        this.checkedOut = false;
        this.checkedOutTo = "";
    }
}
