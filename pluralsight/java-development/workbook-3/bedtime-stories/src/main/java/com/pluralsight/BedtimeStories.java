package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class BedtimeStories {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter the name of the story file: ");
        String fileName = console.nextLine();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                System.out.printf("%d. %s%n", lineNumber, line);
                lineNumber++;
            }
            reader.close();
            console.close();
        } catch (Exception e) {
            System.out.println("File not found: " + fileName);
        }
    }
}

