package com.pluralsight;


public class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Person otherPerson) {
        // Step 1: Compare last names lexicographically (alphabetical order)
        // String.compareTo() returns:
        //   - a negative number if this.lastName comes BEFORE otherPerson.lastName
        //   - 0 if they are EQUAL
        //   - a positive number if this.lastName comes AFTER otherPerson.lastName
        int lastNameComparison = this.lastName.compareTo(otherPerson.lastName);

        // If last names are not equal, return the result immediately
        // This will determine the sort order:
        //   - Negative => this person appears BEFORE the other
        //   - Positive => this person appears AFTER the other
        //   - 0 => continue comparing first names
        if (lastNameComparison != 0) {
            return lastNameComparison; // Primary sorting by last name
        }

        // Step 2: Compare first names if last names are the same
        // Same logic applies:
        //   - Negative => this.firstName comes BEFORE otherPerson.firstName
        //   - Positive => comes AFTER
        //   - 0 => continue comparing age
        int firstNameComparison = this.firstName.compareTo(otherPerson.firstName);

        if (firstNameComparison != 0) {
            return firstNameComparison; // Secondary sorting by first name
        }

        // Step 3: Compare by age if both names are identical
        // Integer.compare(a, b) returns:
        //   - Negative if a < b => this person is YOUNGER
        //   - 0 if a == b => same age
        //   - Positive if a > b => this person is OLDER
        return Integer.compare(this.age, otherPerson.age); // Tertiary sorting by age
    }

    @Override
    public String toString() {
        return lastName + ", " + firstName + ", " + age;
    }
}

