package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("Ray", "Maroun", 55));
        employees.add(new Employee("Potato", "Sensei", 35));
        employees.add(new Employee("John", "Doe", 26));
        employees.add(new Employee("Raymond", "Maroun", 25));

        System.out.println("Enter a last name to search: ");
        Scanner scanner = new Scanner(System.in);
        String lastNameToSearch = scanner.nextLine();

        /*List<Employee> matchingEmployees = new ArrayList<>();

        for (Employee employee : employees) {
            if (employee.getLastName().equalsIgnoreCase(lastNameToSearch)) {
                matchingEmployees.add(employee);
            }
        }*/

        List<Employee> matchingEmployees =
                employees // Source - Data At Rest
                        .stream().filter(employee -> employee.getLastName().equalsIgnoreCase(lastNameToSearch))  // Data Transformation - Data in Transit
                        .toList(); // Destination - Data At Rest

        /*ArrayList<Employee> matchingEmployees =
                employees // Source - Data At Rest
                        .stream().filter(employee -> employee.getLastName().equalsIgnoreCase(lastNameToSearch))  // Data Transformation - Data in Transit
                        .collect(Collectors.toCollection(ArrayList::new)); // Destination - Data At Rest*/

        System.out.println("The results are: ");

        /*for (Employee matchingEmployee : matchingEmployees) {
            System.out.println(matchingEmployee);
        }*/

        //matchingEmployees.stream().forEach(matchingEmployee -> System.out.println(matchingEmployee));
        matchingEmployees.forEach(matchingEmployee -> System.out.println(matchingEmployee));

        System.out.println("==================================================================");

        employees.forEach(employee -> {
            if (employee.getAge() < 40) {
                System.out.println(employee.getFirstName() + " is young.");
            } else {
                System.out.println(employee.getFirstName() + " is experienced.");
            }
        });
    }
}
