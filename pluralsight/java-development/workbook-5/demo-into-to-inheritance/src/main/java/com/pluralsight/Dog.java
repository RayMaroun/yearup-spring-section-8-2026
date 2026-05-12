package com.pluralsight;

public class Dog extends Animal {
    private String breed;

    public Dog(String name, String breed) {
        super(name);
        System.out.println("I am in the Dog Constructor with 2 params");
        this.breed = breed;
    }

    public Dog(String name, int age, String breed) {
        super(name, age);
        System.out.println("I am in the Dog Constructor with 3 params");
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void bark() {
        System.out.println(getName() + " says: Ruff, ruff!");
    }
}
