package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of the day you want to display(0-6):");
        int dayNumber = scanner.nextInt();

        String dayName = "";

/*        if (dayNumber == 0) {
            dayName = "Sunday";
        } else if (dayNumber == 1) {
            dayName = "Monday";
        } else if (dayNumber == 2) {
            dayName = "Tuesday";
        } else if (dayNumber == 3) {
            dayName = "Wednesday";
        } else if (dayNumber == 4) {
            dayName = "Thursday";
        } else if (dayNumber == 5) {
            dayName = "Friday";
        } else if (dayNumber == 6) {
            dayName = "Saturday";
        } else {
            System.out.println("Invalid Input!");
            return;
        }*/

        /*switch (dayNumber) {
            case 0:
                dayName = "Sunday";
                break;
            case 1:
                dayName = "Monday";
                break;
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            case 4:
                dayName = "Thursday";
                break;
            case 5:
                dayName = "Friday";
                break;
            case 6:
                dayName = "Saturday";
                break;
            default:
                System.out.println("Invalid Input!");
                return;
        }*/

        switch (dayNumber) {
            case 0 -> dayName = "Sunday";
            case 1 -> dayName = "Monday";
            case 2 -> dayName = "Tuesday";
            case 3 -> dayName = "Wednesday";
            case 4 -> dayName = "Thursday";
            case 5 -> dayName = "Friday";
            case 6 -> dayName = "Saturday";
            default -> {
                System.out.println("Invalid Input!");
                return;
            }
        }

        System.out.println("The name of the day chosen is: " + dayName);
    }
}
