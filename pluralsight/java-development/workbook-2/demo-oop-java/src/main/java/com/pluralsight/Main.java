package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the instructor:");
        String name = scanner.nextLine();

        System.out.println("Enter the age of the instructor:");
        int age = scanner.nextInt();

        Person instructor = new Person(name, age);

        System.out.println(instructor.getName());

        instructor.setName("Ray");
        System.out.println(instructor.getName());

        System.out.println("==============================================");

        System.out.println(instructor.getName());

        System.out.println("==============================================");

        System.out.println("Energy Level Before work: " + instructor.getEnergy());

        instructor.work(5);

        System.out.println("Energy Level After work: " + instructor.getEnergy());

        System.out.println("==============================================");

        instructor.greet("Mohammad");

        System.out.println("==============================================");
        instructor.eat();
        System.out.println("Energy Level After eating: " + instructor.getEnergy());

        System.out.println("==============================================");

        Person student = new Person("Stephen");

        Person student2 = new Person(55);

        System.out.println(student2.getName());

        System.out.println("==============================================");

        Dog dog1 = new Dog();

        dog1.setColor("Black");
        dog1.setAge(12);

        System.out.println("==============================================");

        Car myNewCar = new Car("Audi", "Q8", "Black", 2018, 13000);

        System.out.println(myNewCar.getCurrentSpeed());

        myNewCar.accelerate(10);

        System.out.println(myNewCar.getCurrentSpeed());

        myNewCar.brake(5);

        System.out.println(myNewCar.getCurrentSpeed());

    }
}
