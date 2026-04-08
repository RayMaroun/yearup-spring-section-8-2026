package com.pluralsight;

public class Main {
    public static void main(String[] args) {
 /*       String firstWord = "Hello";
        String secondWord = "World";

        String greeting = firstWord + secondWord;
        System.out.println(greeting);*/

/*        double firstNumber = 10;
        double secondNumber = 3;
        double result;

        // add
        result = firstNumber + secondNumber;
        System.out.println(result);

        // minus
        result = firstNumber - secondNumber;
        System.out.println(result);

        // multiply
        result = firstNumber * secondNumber;
        System.out.println(result);

        // division
        result = firstNumber / secondNumber;
        System.out.println(result);

        // modulo
        result = firstNumber % secondNumber;
        System.out.println(result);

        result = secondNumber - (firstNumber % secondNumber);
        System.out.println(result);*/

        int x = 5;

/*        x++; //x = x + 1;
        ++x;

        System.out.println(x);*/

/*        int y;
        System.out.println("==========================================");
        y = ++x;
        // 1- Take the value of x, which = 5;
        // 2- Add +1 to the value of x, so now x = 6;
        // 3- Take the value from x and give now to y, so y = 6;

        System.out.println(y);
        System.out.println(x);

        y = x++;
        // 1- Take the value of x, which = 5;
        // 2- Take the value from x and give it now to y, y = 5;
        // 3- Add +1 to the value of x, so now x = 6;

        System.out.println(y);
        System.out.println(x);*/

/*        long firstNumber = 3000000000L;

        float secondNumber = 3.45f;

        long ssn = 111_22_3333L;

        int testNumber = 123_444_555_6;*/

/*        // Widening
        int myInt = 9;
        long myLong;
        myLong = myInt;

        float myFloat = 3.8f;
        double myDouble;

        myDouble = myFloat;*/


        System.out.println("===========================================");

        // Narrowing
        int myInt;
        long myLong = 9123123312312323128L;

        System.out.println(myLong);

        myInt = (int) myLong;

        System.out.println(myInt);

        System.out.println("===========================================");

        float myFloat;
        double myDouble = 123.4567890123;

        System.out.println(myDouble);

        myFloat = (float) myDouble;

        System.out.println(myFloat);



    }
}
