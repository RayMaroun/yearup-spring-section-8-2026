package com.pluralsight;

public class Main {
    /*public static String lastName = "Maroun";

    public static void main(String[] args) {
        System.out.println(lastName);
        greetEveryone();
    }

    public void printLastName() {
        System.out.println(lastName);
    }

    public static void greetEveryone() {
        System.out.println("Hello Everyone did you miss me?");
    }*/

    public static void main(String[] args) {
        Student alice = new Student("Alice", 10);
        Student bob = new Student("Bob", 9);

        alice.printDetails();
        bob.printDetails();

        Student.changeSchool("Lakeside Academy");

        System.out.println("===========================================");

        alice.printDetails();
        bob.printDetails();

        Math potato = new Math();
    }
}
