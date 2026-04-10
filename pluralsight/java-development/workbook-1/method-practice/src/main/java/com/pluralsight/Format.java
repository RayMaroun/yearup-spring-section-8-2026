package com.pluralsight;

public class Format {
    public static void main(String[] args) {
        // Call method directly inside println
        System.out.println(formatName("Bob", "Smith"));

        // Bonus: store result in variable
        String formatted = formatName("Alice", "Johnson");
        System.out.println(formatted);
    }

    public static String formatName(String first, String last) {
        return last + ", " + first;
    }
}
