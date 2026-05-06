package com.pluralsight;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Raymond", "Maroun", 55);
        System.out.println(person1.getLastName());

        /*Person person1 = new Person();
        person1.setFirstName("Raymond");
        person1.setLastName("Maroun");
        person1.setAge(55);*/

        Person person2;
        person2 = new Person("Potato", "Sensei", 33);

        System.out.println(person2);

        System.out.println(person1.getFullName());
        System.out.println(person2.getPersonalDetails());

        System.out.println("=========================================================");

        System.out.println(person2.getEnergy());

        person2.eat();

        System.out.println(person2.getEnergy());

        System.out.println("=========================================================");

        person1.greet(person2);

        System.out.println("=========================================================");

        Person person3 = new Person(55, "Ray");


    }
}
