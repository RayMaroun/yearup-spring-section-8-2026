package com.pluralsight;

public class Main {
    public static void main(String[] args) {
        /*String fullName = "Raymond\rMar";

        System.out.println(fullName);*/

/*        System.out.print("Hello World");
        System.out.print("\rHi");*/

/*        String s1 = "ABC";

        String prefix = "A";
        String s2 = prefix + "BC";
        //String s2 = new String("ABC");

        System.out.println("This is s1: " + s1);
        System.out.println("This is s2: " + s2);

        if (s1 == s2) {
            System.out.println("Both strings are the same!");
        } else {
            System.out.println("Both strings are not the same!");
        }*/

        String fullName = "Raymond Maroun";

        int length = fullName.length();
        System.out.println(length);

        System.out.println("===================================================");

        String username = "   danaw        ";
        System.out.println(username);

        String trimmedUserName = username.trim();
        System.out.println(trimmedUserName);

        System.out.println("===================================================");

        String name = "raymond";
        System.out.println(name.toUpperCase());

        System.out.println("===================================================");
        String newName = "ROGER";

        System.out.println(newName.toLowerCase());

        System.out.println("===================================================");

        String instructorName = "      raymond       ";
        System.out.println(instructorName.trim().toUpperCase());

        System.out.println("===================================================");

        String secondName = "Raymond";

        if (secondName.toLowerCase().startsWith("ray")) {
            System.out.println("This is true");
        } else {
            System.out.println("This is false");
        }

        System.out.println("===================================================");

        String discuntCode = "SAVE-50";

        if (discuntCode.toLowerCase().endsWith("-50")) {
            System.out.println("This is true");
        } else {
            System.out.println("This is false");
        }

        System.out.println("===================================================");

        String product = "Spaghetti";

        char letter = product.charAt(5);
        System.out.println(letter);

        System.out.println("===================================================");
        String productCode = "ACME-12213-potato-123-567";
        int dashPosition = productCode.lastIndexOf("-");
        System.out.println(dashPosition);

        System.out.println("===================================================");

        String studentName = "Zain Ashraf";

        String firstName = studentName.substring(0, 4);
        System.out.println(firstName);

        String lastName = studentName.substring(5);
        System.out.println(lastName);

        System.out.println("===================================================");
        String potato = "";

        if (potato.isEmpty()) {
            System.out.println("This is empty!");
        }

        System.out.println("===================================================");
        String personName = "raymond";
        //String fixedName = String.valueOf(personName.charAt(0)).toUpperCase()  + personName.substring(1);
        String fixedName = personName.substring(0, 1).toUpperCase() + personName.substring(1);

        System.out.println(fixedName);
    }
}
