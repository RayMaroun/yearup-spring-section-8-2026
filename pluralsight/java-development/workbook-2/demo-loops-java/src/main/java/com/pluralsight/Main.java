package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
/*        System.out.println(0);
        System.out.println(1);
        System.out.println(2);
        System.out.println(3);
        System.out.println(4);
        System.out.println(5);
        System.out.println(6);
        System.out.println(7);
        System.out.println(8);
        System.out.println(9);*/

/*        int i = 0;

        while (i < 10) {
            System.out.println(i);
            i++; // i = i + 1
        }*/

/*        String operation = "";
        Scanner scanner = new Scanner(System.in);

        while (!operation.equalsIgnoreCase("exit")) {
            System.out.println("What is the operation that you want: ");
            operation = scanner.nextLine();
            if (operation.equalsIgnoreCase("exit")) {
                System.out.println("We are done!");
            } else {
                System.out.println("We continue;");
            }
        }*/

/*        System.out.println("===============================================");
        int i = 0;

        do {
            System.out.println(i);
            i++; // i = i + 1
        } while (i < 10);*/


/*        System.out.println("===============================================");
        int sum = 0;
        int i = 0;

        while (i <= 10) {
            sum += i;
            i++;
        }

        System.out.println(sum);*/

/*        System.out.println("===============================================");

        int sum = 0;
        int i = 11;

        do {
            sum += i;
            i++;
        } while (i <= 10);

        System.out.println(sum);*/

        System.out.println("===============================================");

        int sum = 0;

        for (int i = 1; i <= 10; i++) {
/*            if (i % 3 == 0) {
                continue;
            }
            sum += i;*/

            if (i % 3 != 0) {
                sum += i;
            }

        }

        System.out.println(sum);


/*        System.out.println("===============================================");

        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }*/

/*        System.out.println("===============================================");

        for (int i = 10; i > 0 ; i--) {
            System.out.println(i);
        }*/

    }
}
