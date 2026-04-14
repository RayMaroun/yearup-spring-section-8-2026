package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StringBuilder skills = new StringBuilder();

        skills.append("Git, ");
        skills.append("HTML, ");
        skills.append("CSS, ");
        skills.append("and Bootstrap\n");
        skills.append("JavaScript, ");
        skills.append("ES6, ");
        skills.append("jQuery, ");
        skills.append("REST APIs, ");
        skills.append("and Express\n");
        skills.append("Angular\n");
        skills.append("Java\n");

        Scanner scanner = new Scanner(System.in);

        System.out.println("How many cars you want to buy?");
        int numberOfCars = scanner.nextInt();

        skills.append("How many cars you want to buy?");
        skills.append(" ").append(numberOfCars).append("\n");

        StringBuilder skills2 = new StringBuilder();

        skills.append("Git, ");
        skills.append("HTML, ");
        skills.append("CSS, ");
        skills.append("and Bootstrap\n");


        skills.append(skills2);

        String mySkills = skills.toString();
        System.out.println(mySkills);





    }
}
