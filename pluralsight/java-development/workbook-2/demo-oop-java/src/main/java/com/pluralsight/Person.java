package com.pluralsight;

public class Person {
    // Attributes or Instance Variables or Properties
    private String name;
    private int age;
    private int energy;

    // Constructor

    // person
    public Person() {
    }

    // person_string_int
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.energy = 100;
    }

    // person_string
    public Person(String name) {
        this.name = name;
    }

    // person_int
    public Person(int age) {
        this.age = age;
    }

    // person_int_string
    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }


    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getEnergy() {
        return energy;
    }

    // Actions
    public void work(int hours) {
        this.energy -= (hours * 10);
    }


    // sleep_int
    public void sleep(int hours) {
        this.energy += (hours * 10);
    }

    // eat
    public void eat() {
        this.energy += 20;
    }

    // eat_int
    public void eat(int energyEaten) {
        this.energy += energyEaten;
    }

    // greet_String
    public void greet(String personToGreet) {
        System.out.println(this.name + " is saying Hi to " + personToGreet);
    }


/*    // greet_String
    public void greet(String studentName){
        System.out.println("Hello Student:" + studentName);
    }*/

    // greet
    public void greet() {
        System.out.println(this.name + " is saying Hi to Everyone!");
    }


}
