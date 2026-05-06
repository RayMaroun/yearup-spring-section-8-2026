package com.pluralsight;

public class Employee {
    private String name;
    private int age;
    private double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    // -------------------- High Cohesion Methods --------------------

    // Calculates a 10% salary bonus
    public double calculateBonus() {
        return this.salary * 0.10;
    }

    // Returns a formatted string of employee details
    public String getDetails() {
        return "Name: " + name + ", Age: " + age + ", Salary: $" + salary;
    }

    // Checks if employee is eligible for retirement
    public boolean isEligibleForRetirement() {
        return this.age >= 60;
    }


    public void applyRaise(double percentage) {
        if (percentage > 0) {
            this.salary += this.salary * (percentage / 100);
        }
    }

    // Returns the annual salary based on monthly salary
    public double getAnnualSalary() {
        return this.salary * 12;
    }

    // Determines a simple tax bracket
    public String getTaxBracket() {
        if (salary > 100000) {
            return "High";
        } else if (salary > 50000) {
            return "Medium";
        } else {
            return "Low";
        }
    }

    // -------------------- Low Cohesion Methods (Unrelated) --------------------

    // Simulates document printing (not related to Employee logic)
    public void printDocument(String document) {
        System.out.println("Printing: " + document);
    }

    // Simulates a network connection (not related to Employee logic)
    public void connectToNetwork(String networkName) {
        System.out.println("Connecting to network: " + networkName);
    }
}
