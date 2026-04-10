package com.pluralsight;

public class CompareNumbers {
    public static void main(String[] args) {
        // Testing methods
        System.out.println(isEven(5));     // false
        System.out.println(isEven(10));    // true
        System.out.println(isPositive(-3.5));  // false
        System.out.println(isPositive(7.2));   // true
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static boolean isPositive(double number) {
        return number > 0;
    }
}
