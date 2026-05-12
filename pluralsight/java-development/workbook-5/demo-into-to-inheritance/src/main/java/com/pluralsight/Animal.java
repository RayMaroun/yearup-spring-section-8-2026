package com.pluralsight;

public class Animal {
    private String name;
    private int age;

/*    public Animal() {
        System.out.println("I am in the Animal Empty Constructor!");
    }*/

    public Animal(String name) {
        System.out.println("I am in the Animal Constructor with 1 param");
        this.name = name;
    }

    public Animal(String name, int age) {
        System.out.println("I am in the Animal Constructor with 2 params");
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
