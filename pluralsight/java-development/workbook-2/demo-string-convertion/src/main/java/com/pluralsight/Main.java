package com.pluralsight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
/*        String stringNumber = "111";
        System.out.println(stringNumber + 1);

        int parsedNumber = Integer.parseInt(stringNumber);

        int result = parsedNumber + 1;
        System.out.println(result);

        float myFloat = 12.34f;*/

        String userInput = "2002-10-17"; // YYYY-MM-DD

        LocalDate birthDay = LocalDate.parse(userInput);
        System.out.println(birthDay);

        System.out.println("==================================================");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the date that you want (d/MMM/yyyy):");
        String dateInputFromUser = scanner.nextLine();



        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MMM/yyyy");
        LocalDate userDate = LocalDate.parse(dateInputFromUser, formatter);

        System.out.println(userDate);

    }
}


