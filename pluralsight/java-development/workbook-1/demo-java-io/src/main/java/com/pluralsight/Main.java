package com.pluralsight;


import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
/*        System.out.print("Hello ");
        System.out.println("Raymond");
        System.out.println("============================================");

        float subtotal = 22.87f;
        float tax = subtotal * 0.0825f;
        float totalDue = subtotal + tax;
        System.out.println(totalDue);

        // 1- We create the formatted String
        String formattedTotalDue = String.format("%.2f", totalDue);
        // 2- We print it
        System.out.println("Total due is: " + formattedTotalDue);

        System.out.println("============================================");

        // 1- We format the String and we print it immediately
        System.out.printf("Total due is: %.2f\n", totalDue);


        System.out.println("============================================");
        int id = 10135;
        String name = "Brandon Plyers";
        float pay = 5239.779f;

        System.out.printf("%s (id:%d) $%.2f%n", name, id, pay);
        System.out.println("============================================");

        //System.out.printf("|%-10s|", "Hi");
        System.out.printf("%,d", 100000000);*/

/*        Scanner myScanner = new Scanner(System.in);

        System.out.println("Please Enter your Name: ");
        String name = myScanner.nextLine();

        System.out.println("Hello Everyone, I am " + name + " and I love Potatoes!");

        System.out.println("============================================================");

        System.out.println("Please Enter a Number: ");
        int firstNumber = myScanner.nextInt();
        firstNumber++;
        System.out.println("The updated number is: " + firstNumber);

        myScanner.close();*/

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter the first number: ");
        int firstNumber = myScanner.nextInt();

        System.out.println("Enter the second number: ");
        int secondNumber = myScanner.nextInt();
        myScanner.nextLine(); // Consume the extra space in the memory

        int result = firstNumber + secondNumber;
        System.out.println("The result is: " + result);

        System.out.println("Which operation you would like to do next?");
        String operation = myScanner.nextLine();

        System.out.println("The new operation is: " + operation);

        System.out.println("Enter a number: ");
        int thirdNumber = myScanner.nextInt();
        myScanner.nextLine();

        System.out.println("Enter a simple message: ");
        String simpleMessage = myScanner.nextLine();

        myScanner.close();


    }
}
