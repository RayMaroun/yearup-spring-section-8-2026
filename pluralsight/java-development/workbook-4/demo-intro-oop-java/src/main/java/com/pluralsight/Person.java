package com.pluralsight;

public class Person {
    // Attributes or Instance Variables or Fields or Properties
    private String firstName;
    private String lastName;
    private int age;
    private int energy;
    private boolean isStudent;

    // Constructor

    // person
    public Person() {
    }

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.energy = 100;
    }

    // person_String_String
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.energy = 100;
    }

    // person_int_String
    public Person(int age, String firstName) {
        this.age = age;
        this.firstName = firstName;
        this.energy = 100;
    }

    // person_String_int
    public Person(String lastName, int age) {
        this.lastName = lastName;
        this.age = age;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    // Derived Getters
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getPersonalDetails() {
        return "FirstName: " + firstName + " Age: " + age;
    }

    public String getFullInfo() {
        return "FullName: " + getFullName() + " Age: " + age;
    }

    // Actions or Methods

    // eat
    public void eat() {
        this.energy += 20;
        System.out.println("I am eating potatoes! And I added 20 energy!");
    }

    // eat_int
    public void eat(int energyEaten) {
        this.energy += 20;
        System.out.println("I am eating potatoes! And I added " + energyEaten + " energy!");
    }


    public void work() {
        this.energy -= 20;
        System.out.println("I am working!");
    }

    public void greet(Person person) {
        System.out.println("Hello " + person.getFirstName() + " I am " + this.firstName);
    }

    @Override
    public String toString() {
        return firstName + " | " + lastName + " | " + age;
    }
}
