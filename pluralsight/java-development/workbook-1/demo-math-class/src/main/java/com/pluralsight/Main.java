package com.pluralsight;

public class Main {
    public static void main(String[] args) {
        float value = 1234.567f;
        int wholeNumber = Math.round(value);

        System.out.println(wholeNumber);

        System.out.println("===============================");

        double expressionResult = 1 - 2 * Math.pow(2, 2);
        System.out.println(expressionResult);

        System.out.println("===============================");

        int firstNumber = 5;
        int secondNumber = 10;

        int minimumNumber = Math.min(firstNumber,secondNumber);
        System.out.println(minimumNumber);

        int answer = 0;

        answer = answer + 10; // answer += 10;
        answer = answer - 5; // answer -= 5;
        answer = answer * 10; // answer *= 10;
        answer = answer / 2; // answer /= 2;
        answer = answer % 10; // answer %= 10;

        answer = answer + 1; // answer += 1; // answer++


    }
}
